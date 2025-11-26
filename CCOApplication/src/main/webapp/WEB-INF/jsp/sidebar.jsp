<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<div id="sidebar" class="sidebar-toggle">
	<ul class="nav nav-sidebar">
		<li data-toggle="collapse" href="#chart-types" aria-expanded="false"
			aria-controls="chart-types"><a href="#"> <i
				class="fa fa-area-chart" aria-hidden="true"></i><span>Search
					Criteria</span>
		</a></li>
		<li>
		<%-- <s:form action="searchSales" modelAttribute="sales"
					class="form-horizontal">
			<ul id="chart-types" class="sub-menu">
				
					<li>
						<span class="sidebar-content"> 
							<label class="control-label">Channel</label>
						</span>
					</li>
					<li>
						<s:hidden path="cacheCriteria"/>
						<span class="sidebar-content"> 
							<s:select path="channel" cssClass="input-sm">
								<s:option value="" >Branch Business</s:option>
							</s:select>
						</span>
					</li>
					<li>
						<span class="sidebar-content"> 
							<label class="control-label">SubChannel</label>
						</span>
					</li>
					<li>
						<span class="sidebar-content">
							<s:select path="subChannel" cssClass="input-sm" cssStyle="width:54%;">
								<s:option value="">Dealer</s:option>
							</s:select>
						</span>
					</li>
					<li>
						<span class="sidebar-content"> 
							<label class="control-label">Cluster</label>
						</span>
					</li>
					<li>
						<span class="sidebar-content">
							<s:select path="cluster" cssClass="input-sm" cssStyle="width:54%;">
								<s:option value="">TN & Kerala</s:option>
							</s:select>
						</span>	
					</li>
					<li>
						<span class="sidebar-content"> 
							<label class="control-label">Branch</label>
						</span>
					</li>
					<li>
						<span class="sidebar-content"> 
							<s:select path="branchCode" cssClass="input-sm" cssStyle="width:54%;">
								<s:option value="">C4</s:option>
							</s:select>
						</span>
					</li>
					<li>
						<span class="sidebar-content"> 
							<label class="control-label">LOB</label>
						</span>
					</li>
					<li>
						<span class="sidebar-content"> 
						 	<s:select path="lob"  cssClass="input-sm" cssStyle="width:54%;">
								<s:option value="0">A & H</s:option>
								<s:option value="0">CI</s:option>
								<s:option value="MOTOR" >MOTOR</s:option>
							</s:select>
						</span>
					</li>
					<li>
						<span class="sidebar-content"> 
							<label class="control-label">Period</label>
						</span>
					</li>
					<li>
						<span class="sidebar-content"> 
						 	<s:select path="period" cssClass="input-sm" cssStyle="width:54%;">
								<s:option value="YTD">YTD</s:option>
								<s:option value="MTD">MTD</s:option>
								<s:option value="WTD">WTD</s:option>
								<s:option value="NxtWeek">NxtWeek</s:option>
							</s:select>
						</span>
					</li>
					<li>
						<span class="sidebar-content"> 
							<label class="control-label">Product</label>
						</span>
					</li>
					<li>
						<span class="sidebar-content"> 
						 	<s:select path="product" cssClass="input-sm" cssStyle="width:54%;">
						 		<s:option value="All">Select All</s:option>
								<s:option value="VFA">VFA</s:option>
								<s:option value="VGC">VGC</s:option>
								<s:option value="VMC">VMC</s:option>
								<s:option value="VOC">VOC</s:option>
								<s:option value="VPC">VPC</s:option>
								<s:option value="VPCV">VPCV</s:option>
							</s:select>
						</span>
					</li>
					<li>
						<span class="sidebar-content"> 
						</span>
					</li>
					<li>
						<span class="sidebar-content"> 
							<input type="button" value="Search" id="searchBtn"  class="btn btn-primary">
						</span>
					</li>
			</ul>
			</s:form> --%>
		</li>
	</ul>
</div>