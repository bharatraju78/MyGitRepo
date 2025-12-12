package com.vam.cco.services;

import com.vam.cco.model.ResourceDetailModel;
import com.vam.cco.model.ResourceInfoModel;

public interface ResourceInfoService {
    ResourceDetailModel addResourceToDetail(ResourceDetailModel detailModel);
    ResourceDetailModel removeResourceFromDetail(ResourceDetailModel detailModel, int index);
    ResourceDetailModel calculate(ResourceDetailModel detailModel);
    void getDataFromMasters(ResourceDetailModel detailModel, ResourceInfoModel infoModel);
}