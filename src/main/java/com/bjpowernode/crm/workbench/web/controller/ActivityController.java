package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.HSSFUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.mapper.ActivityRemarkMapper;
import com.bjpowernode.crm.workbench.service.ActivityRemarkService;
import com.bjpowernode.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.workbench.web.controller
 * @Description: TODO
 * @Date 2023-04-12 9:37
 */
@Controller
public class ActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRemarkService activityRemarkService;

    /**
     * 查询所有用户
     *
     * @param request
     * @return
     */
    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request) {
        // 调用service层方法,查询所有的用户
        List<User> usersList = userService.queryAllUsers();
        // 把数据保存到request中
        request.setAttribute("userList", usersList);
        //请求转发到市场活动的主页面
        return "workbench/activity/index";
    }

    /**
     * 保存市场活动
     *
     * @return
     */
    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    public @ResponseBody
    Object saveCreateActivity(Activity activity, HttpSession session) {
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        // 封装参数 id 使用uuid
        activity.setId(UUIDUtils.getUUID());
        // 把当前的系统时间转成一个字符串
        activity.setCreateTime(DateUtils.formateDateTime(new Date()));
        // 获取创建用户ID
        activity.setCreateBy(user.getId());

        ReturnObject returnObject = new ReturnObject();
        try {
            // 调用service层方法,保存创建的市场活动
            int ret = activityService.saveCreateActivity(activity);
            if (ret > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙,请稍后重试....");
            }
        } catch (Exception e) {
            e.printStackTrace();

            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后重试....");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    public @ResponseBody
    Object queryActivityByConditionForPage(String name, String owner, String startDate, String endDate, int pageNo, int pageSize) {
        // 封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        //调用service层方法,查询数据
        List<Activity> activityList = activityService.queryActivityByConditionForPage(map);
        int totalRows = activityService.queryCountOfActivityByCondition(map);
        // 根据查询结果,生成响应信息
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("activityList", activityList);
        retMap.put("totalRows", totalRows);
        return retMap;
    }


    /**
     * 批量删除市场活动
     *
     * @return
     */
    @RequestMapping("/workbench/activity/deleteActivityIds.do")
    public @ResponseBody
    Object deleteActivityIds(String[] id) {
        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法,删除市场活动
            System.out.println(id);
            int ret = activityService.deleteActivityByIds(id);
            if (ret > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙,请稍后重试....");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后重试....");
        }
        return returnObject;
    }

    /**
     * 根据id查询市场活动的信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/workbench/activity/queryActivityById.do")
    public @ResponseBody
    Object queryActivityById(String id) {
        // 调用service层方法,查询市场活动
        Activity activity = activityService.queryActivityById(id);
        // 根据查询结果,返回响应信息
        return activity;
    }

    /**
     * 保存修改的市场活动
     * @return
     */
    @RequestMapping("/workbench/activity/saveEditActivity.do")
    public @ResponseBody
    Object saveEditActivity(Activity activity, HttpSession session) {
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        // 封装参数
        activity.setCreateTime(DateUtils.formateDateTime(new Date()));
        activity.setEditBy(user.getId());
        ReturnObject returnObject = new ReturnObject();

        try {
            // 调用service层方法,保存修改的市场活动
            int ret = activityService.saveEditActivity(activity);
            if (ret > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙,请稍后重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后重试");
        }
        return returnObject;
    }


    /**
     * 文件下载
     */
    @RequestMapping("/workbench/activity/fileDownload.do")
    public void fileDownload(HttpServletResponse response) throws IOException {
        // 1).设置响应类型
        response.setContentType("application/octet-stream;charset=UTF-8");
        // 2. 获取输出流
        OutputStream out = response.getOutputStream();

        // 浏览器接收到响应信息之后,默认情况下,直接在显示窗口中打开响应信息: 即使打不开,也会调用应用程序打开:只有实在打不开,才会激活文件下载窗口
        // 可以设置响应头信息,使浏览器收到响应信息之后,直接激活文件下载窗口,即使能打开也不打开
        response.addHeader("Content-Disposition", "attachment;filename=mystudentList.xls");

        // 读取excel文件(IinputStream),把输出到浏览器(OutoutStream)
        InputStream is = new FileInputStream("D:\\Dir\\serverDir\\studentList.xls");
        byte[] buff = new byte[256];
        int len = 0;
        while ((len = is.read(buff)) != -1) {
            out.write(buff, 0, len);
        }
        // 关闭资源
        is.close();
        out.flush();
    }

    /**
     * 文件下载
     */
    @RequestMapping("/workbench/activity/exportAllActivitys.do")
    public void exportAllActivitys(HttpServletResponse response) throws Exception {
        //调用service层方法，查询所有的市场活动
        List<Activity> activityList = activityService.queryAllActivitys();
        //创建exel文件，并且把activityList写入到excel文件中
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");

        //遍历activityList，创建HSSFRow对象，生成所有的数据行
        if (activityList != null && activityList.size() > 0) {
            Activity activity = null;
            for (int i = 0; i < activityList.size(); i++) {
                activity = activityList.get(i);

                //每遍历出一个activity，生成一行
                row = sheet.createRow(i + 1);
                //每一行创建11列，每一列的数据从activity中获取
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
        //根据wb对象生成excel文件
//        OutputStream os=new FileOutputStream("D:\\Dir\\serverDir\\activityList.xls");
//        wb.write(os);
        //关闭资源
//        os.close();
//        wb.close();

        //把生成的excel文件下载到客户端
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=activityList.xls");
        OutputStream out = response.getOutputStream();
       /* InputStream is=new FileInputStream("D:\\Dir\\serverDir\\activityList.xls");
        byte[] buff=new byte[256];
        int len=0;
        while((len=is.read(buff))!=-1){
            out.write(buff,0,len);
        }
        is.close();*/

        wb.write(out);

        wb.close();
        out.flush();
    }

    /**
     * 文件上传
     * 配置springmvc的文件上传解析器
     * @param userName
     * @param myFile
     * @return
     */
    @RequestMapping("/workbench/activity/fileUpload.do")
    public @ResponseBody
    Object fileUpload(String userName, MultipartFile myFile) throws Exception {
        // 把文本数据打印到控制台
        System.out.println("userName=" + userName);
        // 把文件在服务指定的目录中生成一个同样的文件
        String originalFilename = myFile.getOriginalFilename();
        File file = new File("D:\\Dir\\serverDir\\" + originalFilename);// 路径必须手动创建好,文件如果不存在,会自动创建
        myFile.transferTo(file);

        // 返回响应信息
        ReturnObject returnObject = new ReturnObject();
        returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        returnObject.setMessage("上传成功");
        return returnObject;
    }

    /**
     * 文件导入/上传
     * @param activityFile
     * @param session
     * @return
     */
    @RequestMapping("/workbench/activity/importActivity.do")
    public @ResponseBody Object importActivity(MultipartFile activityFile, HttpSession session) {
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        try {
            // 把excel文件写到磁盘目录中
//            String originalFilename = activityFile.getOriginalFilename();
//            File file = new File("D:\\Dir\\serverDir\\" + originalFilename);// 路径必须手动创建好,文件如果不存在,会自动创建
//            activityFile.transferTo(file);

            // 解析excel文件.获取文件中的数据,并且封装成activityList
            // FileInputStream is = new FileInputStream("D:\\Dir\\serverDir\\" + originalFilename);

            InputStream is = activityFile.getInputStream();

            HSSFWorkbook wb = new HSSFWorkbook(is);
            // 根据wb获取HSSFSheet对象,封装了一页的所有信息
            HSSFSheet sheet = wb.getSheetAt(0);//页的下标,下标从0开始,依次增加
            // 根据sheet获取HSSFRow对象,封装了一行的所有信息
            HSSFRow row = null;
            HSSFCell cell = null;
            Activity activity = null;
            List<Activity> activityList = new ArrayList<Activity>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // sheet.getLastRowNum()最后一行的下标
                row = sheet.getRow(i);// 行的下标,下标从0开始,依次增加
                activity = new Activity();
                activity.setId(UUIDUtils.getUUID());
                activity.setOwner(user.getId());
                activity.setCreateTime(DateUtils.formateDateTime(new Date()));
                activity.setCreateBy(user.getId());

                for (int j = 0; j < row.getLastCellNum(); j++) {  //  row.getLastCellNum()最后一列的下标
                    // 根据row获取HSSFCell对象,封装了一列的所有信息
                    cell = row.getCell(j); // 列的下标,下标从0开始,依次增加

                    // 获取列中的数据
                    String cellValue = HSSFUtils.getCellValueForStr(cell);
                    if (j == 0) {
                        activity.setName(cellValue);
                    } else if (j == 1) {
                        activity.setStartDate(cellValue);
                    } else if (j == 2) {
                        activity.setEndDate(cellValue);
                    } else if (j == 3) {
                        activity.setCost(cellValue);
                    } else if (j == 4) {
                        activity.setDescription(cellValue);
                    }
                }

                // 每一行中所有列都封装完成之后,把activity保存到list中
                activityList.add(activity);
            }

            // 调用service层方法,保存市场活动
            int ret = activityService.saveCreateActivityByList(activityList);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetData(ret);

        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后重试....");
        }
        return returnObject;
    }


    @RequestMapping("/workbench/activity/detailActivity.do")
    public String detailActivity(String id,HttpServletRequest request){
        // 调用service层方法,查询数据
        Activity activity = activityService.queryActivityForDetailById(id);
        List<ActivityRemark> remarkList = activityRemarkService.queryActivityRemarkForDetailByActivityId(id);
        // 把数据保存到request中
        request.setAttribute("activity",activity);
        request.setAttribute("remarkList",remarkList);
        // 请求转发
        return "workbench/activity/detail";
    }
}
