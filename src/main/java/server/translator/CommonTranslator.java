package server.translator;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CommonTranslator<TSource, TDest>
{
    private Class<TSource> sourceClass;
    private Class<TDest> destClass;

    @SuppressWarnings("unchecked")
    protected CommonTranslator()
    {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;

        this.sourceClass = (Class<TSource>)paramType.getActualTypeArguments()[0];
        this.destClass = (Class<TDest>)paramType.getActualTypeArguments()[1];
    }

    protected TDest newDestInstance()
            throws IllegalAccessException, InstantiationException
    {
        return destClass.newInstance();
    }

    protected TSource newSourceInstance()
            throws IllegalAccessException, InstantiationException
    {
        return sourceClass.newInstance();
    }

    public void businessToData(
            TSource source,
            TDest data)
            throws Exception
    {
    }

    public TDest businessToData(
            TSource source)
            throws Exception
    {
        TDest data = newDestInstance();
        businessToData(source, data);
        return data;
    }

    public void businessListToDataList(
            List<TSource> source,
            List<TDest> data)
            throws Exception
    {
        if (source == null) {
            return;
        }
        for (TSource detail : source) {
            if (detail != null && isAddToDataList(detail)) {
                data.add(businessToData(detail));
            }
        }
    }

    public List<TDest> businessListToDataList(
            List<TSource> source)
            throws Exception
    {
        List<TDest> data = new ArrayList<TDest>();
        businessListToDataList(source, data);
        return data;
    }

    @SuppressWarnings("unchecked")
    public TDest[] businessListToDataArray(
            List<TSource> source)
            throws Exception
    {
        List<TDest> dataList = businessListToDataList(source);
        return dataList.toArray((TDest[]) Array.newInstance(this.destClass, dataList.size()));
    }

    protected boolean isAddToDataList(
            TSource detail)
    {
        return true;
    }

    public void dataToBusiness(
            TDest data,
            TSource dest)
            throws Exception
    {
    }

    public TSource dataToBusiness(
            TDest data)
            throws Exception
    {
        TSource dest = newSourceInstance();
        dataToBusiness(data, dest);
        return dest;
    }

    public void dataListToBusinessList(
            List<TDest> source,
            List<TSource> data)
            throws Exception
    {
        for (TDest detail : source) {
            if (detail != null) {
                data.add(dataToBusiness(detail));
            }
        }
    }

    public List<TSource> dataListToBusinessList(
            List<TDest> source)
            throws Exception
    {
        List<TSource> data = new ArrayList<TSource>();
        dataListToBusinessList(source, data);
        return data;
    }

    public List<TSource> dataArrayToBusinessList(
            TDest[] source)
            throws Exception
    {
        if (source == null) {
            return new ArrayList<TSource>();
        }
        return dataListToBusinessList(Arrays.asList(source));
    }
}
