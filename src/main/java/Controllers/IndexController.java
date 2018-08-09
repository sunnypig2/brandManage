package Controllers;

import Entity.*;
import Service.IndexService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/7/30.
 */
@Controller
public class IndexController {

    @Resource
    private IndexService indexService;

    @ResponseBody
    @RequestMapping("/getBrandRelation")
    public Map<String,Object> getBrandRelation(){
        Map<String,Object> maps = new HashMap<String, Object>();
        List<relationGroup> ans = indexService.getAllRelationGroup();
        maps.put("total",ans.size());
        maps.put("rows",ans);
//        System.out.println(array.toString());
        return maps;
    }

    @ResponseBody
    @RequestMapping("/getBrandType")
    public Map<String,Object> getBrandType(){
        Map<String,Object> ans = new HashMap<String, Object>();
        List<brandClass> list = indexService.getAllBrandClass();
        for(int i=0;i<list.size();i++){
            list.get(i).setClassName("第"+list.get(i).getClassNo()+"类-"+list.get(i).getClassName());
        }
        ans.put("total",list.size());
        ans.put("rows",list);
        return ans;
    }

    @ResponseBody
    @RequestMapping("/getSameGroup")
    public Map<String,Object> getSameGroup(String classid){
//        System.out.println(classid);
        Map<String,Object> ans = new HashMap<String, Object>();
        if(classid != null){
            ans.put("state","success");
            Integer id = Integer.parseInt(classid);
            List<brandGroup> group = indexService.getBrandGroupByClass(id);
            for(brandGroup brand:group){
                brand.setGroupName("【" + brand.getGroupNo() + "】" +brand.getGroupName());
            }
            ans.put("total",group.size());
            ans.put("rows",group);
        }else{
            ans.put("state","error");
        }


        return  ans;
    }

    @ResponseBody
    @RequestMapping("/getItem")
    public Map<String,Object> getItem(String groupid){
        Map<String,Object> ans = new HashMap<String, Object>();
        if(groupid != null){
            ans.put("state","success");
            Integer id = Integer.parseInt(groupid);
            List<brandItem> group = indexService.getBrandItemByGroup(id);
            for(brandItem brand:group){
                brand.setItemName( brand.getItemNo() + " " +brand.getItemName());
            }
            ans.put("total",group.size());
            ans.put("rows",group);

            System.out.println(groupid);
            System.out.println(group.size());

        }else{
            ans.put("state","error");
        }


        return  ans;
    }

    @ResponseBody
    @RequestMapping(value="/addGroup",method= RequestMethod.POST)
    public Map<String,Object> addGroup(@RequestBody String data){
        JSONObject dataJSON = JSONObject.parseObject(data);
        String classid = dataJSON.getString("classid");
        String name = dataJSON.getString("relationName");

        Map<String,Object> ans = new HashMap<String, Object>();
        if(classid != null && name != null){
            relationGroup group = new relationGroup();
            group.setClassId(Integer.parseInt(classid));
            group.setType(0);
            List<relationGroup> list = indexService.findRelationGroupByNameAndClass(name,Integer.parseInt(classid));
            Integer now = list.size() +1;
            group.setRelationName(name+now);
            if(indexService.insertRelationGroup(group) == 1){
                ans.put("state","success");
            }else{
                ans.put("state","error");
            }

        }else{
            ans.put("state","error");
        }

        return  ans;
    }
    @ResponseBody
    @RequestMapping("/addRelationItem")
    public Map<String,Object> addRelationItem(@RequestBody String data){
        Map<String,Object> ans = new HashMap<String, Object>();
        JSONObject object = JSONObject.parseObject(data);
        String isBrother = object.getString("isBrother");

        if(isBrother.equals("1")){
            String relationId = object.getString("relationId");
            String items = object.getString("items");
            String[] arrs = items.split(",");
            for(String arr : arrs){
                relationItem item = new relationItem();
                item.setRelationId(Integer.parseInt(relationId));
                item.setItemId(Integer.parseInt(arr));
                item.setIsParent(new Byte("0"));
                if(indexService.insertRelationItem(item)!= 1){
                    ans.put("state","error");
                    return ans;
                }
            }

        }else if (isBrother.equals("0")){
            String relationId = object.getString("relationId");
            String parentItem = object.getString("parentItem");
            String childItem = object.getString("childItem");
            String[] arrss = childItem.split(",");

            System.out.println(parentItem);
            relationItem item = new relationItem();
            item.setRelationId(Integer.parseInt(relationId));
            item.setItemId(Integer.parseInt(parentItem));
            item.setIsParent(new Byte("1"));
            indexService.insertRelationItem(item);

            for(String arr : arrss){
                relationItem items = new relationItem();
                items.setRelationId(Integer.parseInt(relationId));
                items.setItemId(Integer.parseInt(arr));
                items.setIsParent(new Byte("0"));
                if(indexService.insertRelationItem(items)!= 1){
                    ans.put("state","error");
                    return ans;
                }
            }
        }

        ans.put("state","success");
        return ans;
    }
}
