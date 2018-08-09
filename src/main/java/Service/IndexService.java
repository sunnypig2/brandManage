package Service;


import Entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/7/30.
 */

public interface IndexService {

    List<relationGroup> getAllRelationGroup();

    List<brandClass> getAllBrandClass();

    List<brandGroup> getBrandGroupByClass(Integer id);

    List<brandItem> getBrandItemByGroup(Integer id);

    List<relationGroup> findRelationGroupByNameAndClass(String name,Integer id);

    int insertRelationGroup(relationGroup group);

    int insertRelationItem(relationItem item);
}
