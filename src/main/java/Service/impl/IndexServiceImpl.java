package Service.impl;

import Dao.*;
import Entity.*;
import org.springframework.stereotype.Service;
import Service.IndexService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/7/30.
 */
@Service()
public class IndexServiceImpl implements IndexService{
    @Resource
    private relationGroupMapper relationGroupmapper;
    @Resource
    private brandClassMapper brandClassmapper;
    @Resource
    private brandGroupMapper brandGroupmapper;
    @Resource
    private brandItemMapper brandItemmapper;
    @Resource
    private relationItemMapper relationItemmapper;

    public List<relationGroup> getAllRelationGroup() {
        return relationGroupmapper.getAllRelationGroup();
    }

    public List<brandClass> getAllBrandClass() {
        return brandClassmapper.getAllBrandClass();
    }

    public List<brandGroup> getBrandGroupByClass(Integer id) {
        return brandGroupmapper.getBrandGroupByClass(id);
    }

    public List<brandItem> getBrandItemByGroup(Integer id) {
        return brandItemmapper.getItemByGroup(id);
    }

    public List<relationGroup> findRelationGroupByNameAndClass(String name,Integer id) {
        Map<String,Object> ans = new HashMap<String, Object>();
        ans.put("classId",id);
        ans.put("relationName",name);
        return relationGroupmapper.getRelationGroupByNameAndClass(ans);
    }

    public int insertRelationGroup(relationGroup group) {
        return relationGroupmapper.insert(group);
    }

    public int insertRelationItem(relationItem item) {
        return relationItemmapper.insert(item);
    }
}
