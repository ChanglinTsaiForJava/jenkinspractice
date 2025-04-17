package tw.com.ispan.dto;

import java.util.List;

import tw.com.ispan.domain.ProductBean;

public class ProductResponse {
    private Boolean success;
    private String message;
    private Long count;
    private List<ProductBean> list;

    @Override
    public String toString() {
        return "ProductResponse [success=" + success + ", message=" + message + ", count=" + count + ", list=" + list
                + "]";
    }
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }
    public List<ProductBean> getList() {
        return list;
    }
    public void setList(List<ProductBean> list) {
        this.list = list;
    }
}
