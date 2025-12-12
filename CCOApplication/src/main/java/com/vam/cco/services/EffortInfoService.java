package com.vam.cco.services;

import com.vam.cco.model.EffortDetailModel;

public interface EffortInfoService {
    EffortDetailModel addEffortToDetail(EffortDetailModel detailModel);
    EffortDetailModel removeEffortFromDetail(EffortDetailModel detailModel, int index);
    EffortDetailModel calculate(EffortDetailModel detailModel);
}
