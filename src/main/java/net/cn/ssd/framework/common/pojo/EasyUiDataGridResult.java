package net.cn.ssd.framework.common.pojo;

import java.util.List;

public class EasyUiDataGridResult {
    private static final long serialVersionUID = 1479299591289375505L;
    private long total;
    private List rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
