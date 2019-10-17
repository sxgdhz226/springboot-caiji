package net.cn.ssd.quartz;

import net.cn.ssd.framework.common.pojo.ProjectProperties;
import net.cn.ssd.mapper.WeatherDao;
import net.cn.ssd.util.GetSqlUtil;
import net.cn.ssd.util.WriteFileUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WeatherTask extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(WeatherTask.class);

    @Autowired
    private WeatherDao weatherDao;

    @Autowired
    private ProjectProperties projectProperties;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //  这里写调用执行定时调度任务的逻辑
        if (logger.isInfoEnabled()){
            logger.info("------定时查询dbssdweahter实况库-------" + new Date());
        }

        List<String> sqlList = GetSqlUtil.parseXml();
        for (String sql : sqlList){
            Map<String, Object> resultMap = jdbcTemplate.queryForObject(sql, new RowMapper<Map<String,Object>>() {

                @Override
                public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                    Map<String, Object> map = new HashMap<>();
                    map.put("result", resultSet.getString("result"));
                    return map;
                }
            });
            write(resultMap);
        }
    }


    public void write(Map<String,Object> resultmap){
        try {
            String fileName = "";
            String savePath = projectProperties.getSaveDir();
            String weatherDesc = resultmap.get("result").toString();
            if (weatherDesc != null && !weatherDesc.equals("")){
                fileName = weatherDesc.substring(weatherDesc.indexOf("【")+1, weatherDesc.lastIndexOf("】"));
                File file = new File(savePath);
                if (!file.exists()){
                    file.mkdir();
                }

                if (logger.isInfoEnabled()){
                    logger.info("实况内容："+weatherDesc+"\r\n"+"文件保存目录："+savePath);
                }
                String [] weahterArr = weatherDesc.split(",");
                String newStr = "";
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                for (int i = 0 ; i < weahterArr.length ; i++){
                    if (weahterArr[i].contains("(新天泽)")){
                        String temp = weahterArr[i].substring(weahterArr[i].indexOf("("),weahterArr[i].lastIndexOf(")")+1 );
                        weahterArr[i] = weahterArr[i].replace(temp,"" );
                    }
                    Matcher m = p.matcher(weahterArr[i]);
                    newStr += String_length(m.replaceAll(""),projectProperties.getBoolval(),new String(projectProperties.getPlaceHolder().getBytes("ISO-8859-1"),"UTF-8"));
                }
                WriteFileUtil.writeContent(savePath+ File.separatorChar+fileName+".txt", newStr,false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String String_length(String value,int totalLen,String placeHolder) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese) || placeHolder.contains(temp)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        if (valueLength < totalLen) {
            int temp = totalLen - valueLength;
            for (int i = 0; i < temp; i++) {
                value+= " ";
            }
        }
        return value;
    }
}
