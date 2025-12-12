<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- Fragment to render the contents of #resourceDetailsContainer. Expects model attribute 'detail' of type ResourceDetailModel --%>
<c:if test="${not empty teaEstimate.resourceDetail}">                            
	<c:set var="rd" value="${detail != null ? detail : teaEstimate.resourceDetail}" />
	<c:if test="${not empty rd}">
	    <div class="card mb-3 nested-card resource-detail">
	        <div class="card-body">
	        	<input type="hidden" name="resourceDetail.resDetailId" value="${rd.resDetailId}" />
	            <div class="mt-3">
	                <div class="d-flex justify-content-between align-items-center mb-2">
	                    <h6 class="mb-0">Resource Info</h6>
	                    <button type="button" class="btn btn-sm btn-outline-primary js-add-resoure-info">+ Add Resource</button>
	                </div>
	                <div class="resource-info-container">
	                    <table class="table table-striped table-bordered table-hover resource-info-table">
	                        <thead>
	                        	<tr bgcolor="#A6A6A6" style="background-color: #16365C; font-weight: bold; color: white;">
	                        		<th>ID</th>
	                                <th>Track</th>
	                                <th>Location</th>
	                                <th>Role</th>
	                                <th>Role Type</th>
	                                <th>Shift</th>
	                                <th>Employee Type</th>
	                                <th>VM Band</th>
	                                <th>Rate</th>
	                                <th>Hour/ Month</th>
									<th colspan="12" class="text-center" >Year-1</th>
	                      			<th colspan="12" class="text-center" >Year-2</th>
	                      			<th colspan="12" class="text-center" >Year-3</th>
	                      			<th colspan="12" class="text-center" >Year-4</th>
	                      			<th colspan="1" class="text-center" >Year-1 total</th>
	                      			<th colspan="1" class="text-center" >Year-2 total</th>
	                      			<th colspan="1" class="text-center" >Year-3 total</th>
	                      			<th colspan="1" class="text-center" >Year-4 total</th>
	                      			<th colspan="1" class="text-center" >Overall total</th>
	                      			<th colspan="1" class="text-center" >Action</th>
								</tr>
	                            <tr bgcolor="#A6A6A6" style="background-color: #16365C; font-weight: bold; color: white;">
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                                <th>Jan</th>
	                                <th>Feb</th>
	                                <th>Mar</th>
	                                <th>Apr</th>
	                                <th>May</th>
	                                <th>Jun</th>
	                                <th>Jul</th>
	                                <th>Aug</th>
	                                <th>Sep</th>
	                                <th>Oct</th>
	                                <th>Nov</th>
	                                <th>Dec</th>
	                                <th>Jan</th>
	                                <th>Feb</th>
	                                <th>Mar</th>
	                                <th>Apr</th>
	                                <th>May</th>
	                                <th>Jun</th>
	                                <th>Jul</th>
	                                <th>Aug</th>
	                                <th>Sep</th>
	                                <th>Oct</th>
	                                <th>Nov</th>
	                                <th>Dec</th>
	                                <th>Jan</th>
	                                <th>Feb</th>
	                                <th>Mar</th>
	                                <th>Apr</th>
	                                <th>May</th>
	                                <th>Jun</th>
	                                <th>Jul</th>
	                                <th>Aug</th>
	                                <th>Sep</th>
	                                <th>Oct</th>
	                                <th>Nov</th>
	                                <th>Dec</th>
	                                <th>Jan</th>
	                                <th>Feb</th>
	                                <th>Mar</th>
	                                <th>Apr</th>
	                                <th>May</th>
	                                <th>Jun</th>
	                                <th>Jul</th>
	                                <th>Aug</th>
	                                <th>Sep</th>
	                                <th>Oct</th>
	                                <th>Nov</th>
	                                <th>Dec</th>
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                                <th>&nbsp;</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            <c:forEach var="ri" items="${rd.resourceInfos}" varStatus="riStatus">
	                                <tr data-index="${riStatus.index}">
	                                    <td><input class="form-control" style="width: 80px" type="number" readonly="readonly" name="resourceDetail.resourceInfos[${riStatus.index}].infoId" value="${ri.infoId}" /></td>
	                                    <td><input class="form-control" style="width: 300px" type="text" name="resourceDetail.resourceInfos[${riStatus.index}].track" value="${ri.track}" placeholder="Track" /></td>
	                                    <td>
	                                    	<select style="width: 100px" class="form-control" id="resourceDetail.resourceInfos[${riStatus.index}].location" name="resourceDetail.resourceInfos[${riStatus.index}].location">
					                            <c:forEach var="s" items="${rd.locationList}">
					                                <option value="${s}" ${s == ri.location ? 'selected' : ''}>${s}</option>
					                            </c:forEach>
					                        </select>
	                                    </td>
	                                    <td><input class="form-control" style="width: 300px" type="text" name="resourceDetail.resourceInfos[${riStatus.index}].role" value="${ri.role}" placeholder="Role" /></td>
	                                    <td>
	                                    	<select style="width: 180px" class="form-control" id="resourceDetail.resourceInfos[${riStatus.index}].roleType" name="resourceDetail.resourceInfos[${riStatus.index}].roleType">
					                            <c:forEach var="s" items="${rd.roleTypeList}">
					                                <option value="${s}" ${s == ri.roleType ? 'selected' : ''}>${s}</option>
					                            </c:forEach>
					                        </select>
	                                    </td>
	                                    <td>
	                                    	<select style="width: 100px" class="form-control" id="resourceDetail.resourceInfos[${riStatus.index}].shift" name="resourceDetail.resourceInfos[${riStatus.index}].shift">
					                            <c:forEach var="s" items="${rd.shiftList}">
					                                <option value="${s}" ${s == ri.shift ? 'selected' : ''}>${s}</option>
					                            </c:forEach>
					                        </select>
	                                    	<%-- <input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].shift" value="${ri.shift}" placeholder="Shift" /> --%>
	                                    </td>
	                                    <td>
	                                    	<select style="width: 120px" class="form-control" id="resourceDetail.resourceInfos[${riStatus.index}].employeeType" name="resourceDetail.resourceInfos[${riStatus.index}].employeeType">
					                            <c:forEach var="s" items="${rd.employeeTypeList}">
					                                <option value="${s}" ${s == ri.employeeType ? 'selected' : ''}>${s}</option>
					                            </c:forEach>
					                        </select>
	                                    	<%-- <input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].employeeType" value="${ri.employeeType}" placeholder="Emp Type" /> --%>
	                                    </td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].vmBand" value="${ri.vmBand}" placeholder="VM Band" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].rate" value="${ri.rate}" placeholder="Rate" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].hoursPerMonth" value="${ri.hoursPerMonth}" placeholder="Hours/Month" /></td>
	                                    
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearOneJan" value="${ri.yearOneJan}" placeholder="Jan" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearOneFeb" value="${ri.yearOneFeb}" placeholder="Feb" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearOneMar" value="${ri.yearOneMar}" placeholder="Mar" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearOneApr" value="${ri.yearOneApr}" placeholder="Apr" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearOneMay" value="${ri.yearOneMay}" placeholder="May" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearOneJun" value="${ri.yearOneJun}" placeholder="Jun" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearOneJul" value="${ri.yearOneJul}" placeholder="Jul" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearOneAug" value="${ri.yearOneAug}" placeholder="Aug" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearOneSep" value="${ri.yearOneSep}" placeholder="Sep" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearOneOct" value="${ri.yearOneOct}" placeholder="Oct" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearOneNov" value="${ri.yearOneNov}" placeholder="Nov" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearOneDec" value="${ri.yearOneDec}" placeholder="Dec" /></td>
	                                    
	                                    <td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearTwoJan" value="${ri.yearTwoJan}" placeholder="Jan" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearTwoFeb" value="${ri.yearTwoFeb}" placeholder="Feb" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearTwoMar" value="${ri.yearTwoMar}" placeholder="Mar" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearTwoApr" value="${ri.yearTwoApr}" placeholder="Apr" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearTwoMay" value="${ri.yearTwoMay}" placeholder="May" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearTwoJun" value="${ri.yearTwoJun}" placeholder="Jun" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearTwoJul" value="${ri.yearTwoJul}" placeholder="Jul" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearTwoAug" value="${ri.yearTwoAug}" placeholder="Aug" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearTwoSep" value="${ri.yearTwoSep}" placeholder="Sep" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearTwoOct" value="${ri.yearTwoOct}" placeholder="Oct" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearTwoNov" value="${ri.yearTwoNov}" placeholder="Nov" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearTwoDec" value="${ri.yearTwoDec}" placeholder="Dec" /></td>
										
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearThreeJan" value="${ri.yearThreeJan}" placeholder="Jan" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearThreeFeb" value="${ri.yearThreeFeb}" placeholder="Feb" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearThreeMar" value="${ri.yearThreeMar}" placeholder="Mar" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearThreeApr" value="${ri.yearThreeApr}" placeholder="Apr" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearThreeMay" value="${ri.yearThreeMay}" placeholder="May" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearThreeJun" value="${ri.yearThreeJun}" placeholder="Jun" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearThreeJul" value="${ri.yearThreeJul}" placeholder="Jul" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearThreeAug" value="${ri.yearThreeAug}" placeholder="Aug" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearThreeSep" value="${ri.yearThreeSep}" placeholder="Sep" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearThreeOct" value="${ri.yearThreeOct}" placeholder="Oct" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearThreeNov" value="${ri.yearThreeNov}" placeholder="Nov" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearThreeDec" value="${ri.yearThreeDec}" placeholder="Dec" /></td>
										
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearFourJan" value="${ri.yearFourJan}" placeholder="Jan" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearFourFeb" value="${ri.yearFourFeb}" placeholder="Feb" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearFourMar" value="${ri.yearFourMar}" placeholder="Mar" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearFourApr" value="${ri.yearFourApr}" placeholder="Apr" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearFourMay" value="${ri.yearFourMay}" placeholder="May" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearFourJun" value="${ri.yearFourJun}" placeholder="Jun" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearFourJul" value="${ri.yearFourJul}" placeholder="Jul" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearFourAug" value="${ri.yearFourAug}" placeholder="Aug" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearFourSep" value="${ri.yearFourSep}" placeholder="Sep" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearFourOct" value="${ri.yearFourOct}" placeholder="Oct" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearFourNov" value="${ri.yearFourNov}" placeholder="Nov" /></td>
										<td><input class="form-control" style="width: 80px" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearFourDec" value="${ri.yearFourDec}" placeholder="Dec" /></td>
	                                    
	                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearOneTotal" value="${ri.yearOneTotal}" placeholder="Year-1" /></td>
	                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearTwoTotal" value="${ri.yearTwoTotal}" placeholder="Year-1" /></td>
	                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearThreeTotal" value="${ri.yearThreeTotal}" placeholder="Year-1" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].yearFourTotal" value="${ri.yearFourTotal}" placeholder="Year-1" /></td>
	                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.resourceInfos[${riStatus.index}].overallTotal" value="${ri.overallTotal}" placeholder="Overall" /></td>
	                                    
	                                    <td><button type="button" class="btn btn-sm btn-outline-danger remove-estimation-item" onclick="removeResourceInfo(${riStatus.index});">Remove</button></td>
	                                </tr>
	                            </c:forEach>
	                            	<tr bgcolor="#16365C" style="background-color: #16365C; font-weight: bold; color: white;">
	                            		<td>Team Size:</td>'
	                            		<td>${rd.totalOnsiteTeamSize}</td>
	                            		<td colspan="8">Onsite</td>
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearOneJanTotal" value="${rd.onsiteTotal.yearOneJanTotal}" /></td>
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearOneFebTotal" value="${rd.onsiteTotal.yearOneFebTotal}" /></td>
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearOneMarTotal" value="${rd.onsiteTotal.yearOneMarTotal}" /></td>
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearOneAprTotal" value="${rd.onsiteTotal.yearOneAprTotal}" /></td>
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearOneMayTotal" value="${rd.onsiteTotal.yearOneMayTotal}" /></td>
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearOneJunTotal" value="${rd.onsiteTotal.yearOneJunTotal}" /></td>
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearOneJulTotal" value="${rd.onsiteTotal.yearOneJulTotal}" /></td>
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearOneAugTotal" value="${rd.onsiteTotal.yearOneAugTotal}" /></td>
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearOneSepTotal" value="${rd.onsiteTotal.yearOneSepTotal}" /></td>
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearOneOctTotal" value="${rd.onsiteTotal.yearOneOctTotal}" /></td>
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearOneNovTotal" value="${rd.onsiteTotal.yearOneNovTotal}" /></td>
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearOneDecTotal" value="${rd.onsiteTotal.yearOneDecTotal}" /></td>
	                            		
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearTwoJanTotal" value="${rd.onsiteTotal.yearTwoJanTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearTwoFebTotal" value="${rd.onsiteTotal.yearTwoFebTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearTwoMarTotal" value="${rd.onsiteTotal.yearTwoMarTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearTwoAprTotal" value="${rd.onsiteTotal.yearTwoAprTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearTwoMayTotal" value="${rd.onsiteTotal.yearTwoMayTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearTwoJunTotal" value="${rd.onsiteTotal.yearTwoJunTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearTwoJulTotal" value="${rd.onsiteTotal.yearTwoJulTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearTwoAugTotal" value="${rd.onsiteTotal.yearTwoAugTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearTwoSepTotal" value="${rd.onsiteTotal.yearTwoSepTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearTwoOctTotal" value="${rd.onsiteTotal.yearTwoOctTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearTwoNovTotal" value="${rd.onsiteTotal.yearTwoNovTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearTwoDecTotal" value="${rd.onsiteTotal.yearTwoDecTotal}" /></td>
										
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearThreeJanTotal" value="${rd.onsiteTotal.yearThreeJanTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearThreeFebTotal" value="${rd.onsiteTotal.yearThreeFebTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearThreeMarTotal" value="${rd.onsiteTotal.yearThreeMarTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearThreeAprTotal" value="${rd.onsiteTotal.yearThreeAprTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearThreeMayTotal" value="${rd.onsiteTotal.yearThreeMayTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearThreeJunTotal" value="${rd.onsiteTotal.yearThreeJunTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearThreeJulTotal" value="${rd.onsiteTotal.yearThreeJulTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearThreeAugTotal" value="${rd.onsiteTotal.yearThreeAugTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearThreeSepTotal" value="${rd.onsiteTotal.yearThreeSepTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearThreeOctTotal" value="${rd.onsiteTotal.yearThreeOctTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearThreeNovTotal" value="${rd.onsiteTotal.yearThreeNovTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearThreeDecTotal" value="${rd.onsiteTotal.yearThreeDecTotal}" /></td>
										
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearFourJanTotal" value="${rd.onsiteTotal.yearFourJanTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearFourFebTotal" value="${rd.onsiteTotal.yearFourFebTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearFourMarTotal" value="${rd.onsiteTotal.yearFourMarTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearFourAprTotal" value="${rd.onsiteTotal.yearFourAprTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearFourMayTotal" value="${rd.onsiteTotal.yearFourMayTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearFourJunTotal" value="${rd.onsiteTotal.yearFourJunTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearFourJulTotal" value="${rd.onsiteTotal.yearFourJulTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearFourAugTotal" value="${rd.onsiteTotal.yearFourAugTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearFourSepTotal" value="${rd.onsiteTotal.yearFourSepTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearFourOctTotal" value="${rd.onsiteTotal.yearFourOctTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearFourNovTotal" value="${rd.onsiteTotal.yearFourNovTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearFourDecTotal" value="${rd.onsiteTotal.yearFourDecTotal}" /></td>
										
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearOneOverallTotal" value="${rd.onsiteTotal.yearOneOverallTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearTwoOverallTotal" value="${rd.onsiteTotal.yearTwoOverallTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearThreeOverallTotal" value="${rd.onsiteTotal.yearThreeOverallTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.yearFourOverallTotal" value="${rd.onsiteTotal.yearFourOverallTotal}" /></td>
										
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.onsiteTotal.overallTotal" value="${rd.onsiteTotal.overallTotal}" /></td>
	                            	</tr>
	                            	<tr bgcolor="#16365C" style="background-color: #16365C; font-weight: bold; color: white;">
	                            		<td>Team Size:</td>'
	                            		<td>${rd.totalOffshoreTeamSize}</td>
	                            		<td colspan="8">Offshore</td>
	                            		<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearOneJanTotal" value="${rd.offshoreTotal.yearOneJanTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearOneFebTotal" value="${rd.offshoreTotal.yearOneFebTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearOneMarTotal" value="${rd.offshoreTotal.yearOneMarTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearOneAprTotal" value="${rd.offshoreTotal.yearOneAprTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearOneMayTotal" value="${rd.offshoreTotal.yearOneMayTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearOneJunTotal" value="${rd.offshoreTotal.yearOneJunTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearOneJulTotal" value="${rd.offshoreTotal.yearOneJulTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearOneAugTotal" value="${rd.offshoreTotal.yearOneAugTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearOneSepTotal" value="${rd.offshoreTotal.yearOneSepTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearOneOctTotal" value="${rd.offshoreTotal.yearOneOctTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearOneNovTotal" value="${rd.offshoreTotal.yearOneNovTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearOneDecTotal" value="${rd.offshoreTotal.yearOneDecTotal}" /></td>
										
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearTwoJanTotal" value="${rd.offshoreTotal.yearTwoJanTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearTwoFebTotal" value="${rd.offshoreTotal.yearTwoFebTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearTwoMarTotal" value="${rd.offshoreTotal.yearTwoMarTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearTwoAprTotal" value="${rd.offshoreTotal.yearTwoAprTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearTwoMayTotal" value="${rd.offshoreTotal.yearTwoMayTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearTwoJunTotal" value="${rd.offshoreTotal.yearTwoJunTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearTwoJulTotal" value="${rd.offshoreTotal.yearTwoJulTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearTwoAugTotal" value="${rd.offshoreTotal.yearTwoAugTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearTwoSepTotal" value="${rd.offshoreTotal.yearTwoSepTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearTwoOctTotal" value="${rd.offshoreTotal.yearTwoOctTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearTwoNovTotal" value="${rd.offshoreTotal.yearTwoNovTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearTwoDecTotal" value="${rd.offshoreTotal.yearTwoDecTotal}" /></td>
										
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearThreeJanTotal" value="${rd.offshoreTotal.yearThreeJanTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearThreeFebTotal" value="${rd.offshoreTotal.yearThreeFebTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearThreeMarTotal" value="${rd.offshoreTotal.yearThreeMarTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearThreeAprTotal" value="${rd.offshoreTotal.yearThreeAprTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearThreeMayTotal" value="${rd.offshoreTotal.yearThreeMayTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearThreeJunTotal" value="${rd.offshoreTotal.yearThreeJunTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearThreeJulTotal" value="${rd.offshoreTotal.yearThreeJulTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearThreeAugTotal" value="${rd.offshoreTotal.yearThreeAugTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearThreeSepTotal" value="${rd.offshoreTotal.yearThreeSepTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearThreeOctTotal" value="${rd.offshoreTotal.yearThreeOctTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearThreeNovTotal" value="${rd.offshoreTotal.yearThreeNovTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearThreeDecTotal" value="${rd.offshoreTotal.yearThreeDecTotal}" /></td>
										
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearFourJanTotal" value="${rd.offshoreTotal.yearFourJanTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearFourFebTotal" value="${rd.offshoreTotal.yearFourFebTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearFourMarTotal" value="${rd.offshoreTotal.yearFourMarTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearFourAprTotal" value="${rd.offshoreTotal.yearFourAprTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearFourMayTotal" value="${rd.offshoreTotal.yearFourMayTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearFourJunTotal" value="${rd.offshoreTotal.yearFourJunTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearFourJulTotal" value="${rd.offshoreTotal.yearFourJulTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearFourAugTotal" value="${rd.offshoreTotal.yearFourAugTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearFourSepTotal" value="${rd.offshoreTotal.yearFourSepTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearFourOctTotal" value="${rd.offshoreTotal.yearFourOctTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearFourNovTotal" value="${rd.offshoreTotal.yearFourNovTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearFourDecTotal" value="${rd.offshoreTotal.yearFourDecTotal}" /></td>
										
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearOneOverallTotal" value="${rd.offshoreTotal.yearOneOverallTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearTwoOverallTotal" value="${rd.offshoreTotal.yearTwoOverallTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearThreeOverallTotal" value="${rd.offshoreTotal.yearThreeOverallTotal}" /></td>
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.yearFourOverallTotal" value="${rd.offshoreTotal.yearFourOverallTotal}" /></td>
										
										<td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="resourceDetail.offshoreTotal.overallTotal" value="${rd.offshoreTotal.overallTotal}" /></td>
	                            	</tr>	
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	        </div>
	    </div>
	</c:if>
</c:if>
<div class="d-flex gap-2">
	<button type="button" class="btn btn-sm btn-outline-primary js-add-resoure-info">+ Add Resource</button>
	<a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/tea-estimate/list">Cancel</a>
</div>
