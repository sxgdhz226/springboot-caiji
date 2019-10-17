package net.cn.ssd.framework.common.pojo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:properties/config.properties"})
public class ProjectProperties {

    @Value("${saveDir}")
    private String saveDir;

    @Value("${placeHolder}")
    private String placeHolder;

    @Value("${boolval}")
    private int boolval;

    public String getSaveDir() {
        return saveDir;
    }

    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir;
    }

    public int getBoolval() {
        return boolval;
    }

    public void setBoolval(int boolval) {
        this.boolval = boolval;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }
}
