package com.vam.cco.services;

import com.vam.cco.model.EstimateDetailModel;
import com.vam.cco.model.EstimationItemModel;

public interface EstimationItemService {
    EstimateDetailModel addItemToDetail(EstimateDetailModel detailModel);
    EstimateDetailModel removeItemFromDetail(EstimateDetailModel detailModel, int index);
    EstimateDetailModel calculate(EstimateDetailModel detailModel);
    double getCutoffPercentageByName(String name);
    void getDataFromMasters(EstimateDetailModel detailModel, EstimationItemModel itemModel);
}