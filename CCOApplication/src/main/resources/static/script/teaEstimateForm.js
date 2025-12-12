// Helper functions for teaEstimateForm.jsp (centralized)
// Depends on jQuery
(function($){
    'use strict';
	
	$(document).on("change", "input[name^='resourceDetail.resourceInfos']", function () {
	    recalcResourceTableTotals();
	});
	
	function recalcResourceTableTotals() {
	    const years = ["One", "Two", "Three", "Four"];
	    const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
	                    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

	    // Accumulators for vertical totals
	    const verticalTotals = {
	        onsite:  {},
	        offshore: {}
	    };
	    years.forEach(y => {
	        verticalTotals.onsite[y] = {};
	        verticalTotals.offshore[y] = {};
	        months.forEach(m => {
	            verticalTotals.onsite[y][m] = 0;
	            verticalTotals.offshore[y][m] = 0;
	        });
	    });

	    // 1) Row-wise totals + build vertical totals
	    $("tr[data-index]").each(function () {
	        const $row = $(this);

	        // location (Onsite / Offshore / blank)
	        const locationVal = ($row.find("input[name$='.location']").val() || "").trim().toLowerCase();
	        let bucket = null;
	        if (locationVal === "onsite") {
	            bucket = verticalTotals.onsite;
	        } else if (locationVal === "offshore") {
	            bucket = verticalTotals.offshore;
	        }

	        const yearTotals = {
	            One: 0,
	            Two: 0,
	            Three: 0,
	            Four: 0
	        };

	        years.forEach(y => {
	            months.forEach(m => {
	                const field = "year" + y + m; // e.g. yearOneJan

	                // Find the cell in this row
	                const $cell = $row.find("input[name^='resourceDetail.resourceInfos'][name$='." + field + "']");
	                const val = parseFloat($cell.val()) || 0;

	                yearTotals[y] += val;

	                // Add to vertical totals if we know onsite/offshore
	                if (bucket) {
	                    bucket[y][m] += val;
	                }
	            });

	            // Write row-wise year total
	            const totalFieldName = ".year" + y + "Total";
	            $row.find("input[name^='resourceDetail.resourceInfos'][name$='" + totalFieldName + "']")
	                .val(yearTotals[y]);
	        });

	        // Overall row total (sum of year1–year4)
	        const overall = yearTotals.One + yearTotals.Two + yearTotals.Three + yearTotals.Four;
	        $row.find("input[name^='resourceDetail.resourceInfos'][name$='.overallTotal']").val(overall);
	    });

	    // 2) Write vertical totals into onsite/offshore total rows
	    writeTotalsForLocation("onsiteTotal", verticalTotals.onsite, years, months);
	    writeTotalsForLocation("offshoreTotal", verticalTotals.offshore, years, months);
	}

	function writeTotalsForLocation(prefix, bucket, years, months) {
	    // prefix: "onsiteTotal" or "offshoreTotal"
	    years.forEach(y => {
	        let yearSum = 0;

	        months.forEach(m => {
	            const value = bucket[y][m];

	            const fieldName = "resourceDetail." + prefix + ".year" + y + m + "Total";
	            $("input[name='" + fieldName + "']").val(value);

	            yearSum += value;
	        });

	        // yearly overall total
	        const yearOverallName = "resourceDetail." + prefix + ".year" + y + "OverallTotal";
	        $("input[name='" + yearOverallName + "']").val(yearSum);
	    });

	    // grand overall total (all years, all months)
	    let grand = 0;
	    years.forEach(y => {
	        months.forEach(m => {
	            grand += bucket[y][m];
	        });
	    });

	    const overallName = "resourceDetail." + prefix + ".overallTotal";
	    $("input[name='" + overallName + "']").val(grand);
	}



    // Handler for changes in estimation item fields
	$(document).on("change", "input[name^='estimateDetail.estimationItems']", function () {
	    const name  = $(this).attr("name");
	    const value = $(this).val();

	    console.log("Changed field:", name, "value:", value);

	    const match = name.match(/^estimateDetail\.estimationItems\[(\d+)\]\.(.+)$/);
	    if (!match) return;

	    const index = parseInt(match[1], 10);
	    const field = match[2];

	    // Skip fields that are calculated / written by JS to avoid recursion
	    if (field === "threePointEstimate") {
	        return;
	    }

	    recalculateItems(index, field, value);
	});

	// Recalculate dependent fields for a given estimation item row
	function recalculateItems(index, field, value) {
		try {
			let numValue = 0;
			if(value === null || value === undefined){
			    console.log("rawValue is null or undefined");
			}else {
				//alert("rawValue is : "+value);
				numValue = parseFloat(value) || 0;
		    }
		    console.log("Row:", index, "Field:", field, "Value:", numValue);
		    let calculatedThreePointEstimate = 0;
			    // Example: recalc mean for that row
			    // Read all three fields for this index:
			const opt  = parseFloat($(`input[name="estimateDetail.estimationItems[${index}].optimisticPersonHours"]`).val()) || 0;
			const pesi = parseFloat($(`input[name="estimateDetail.estimationItems[${index}].pessimisticPersonHours"]`).val()) || 0;
			const mean = parseFloat($(`input[name="estimateDetail.estimationItems[${index}].meanPersonHours"]`).val()) || 0;
			//alert("opt: "+opt+" , pesi: "+pesi+" , mean: "+mean);
			if(opt > 0 && pesi > 0 && mean > 0){ 
				calculatedThreePointEstimate = (opt + pesi + (4 * mean)) / 6;
				$(`input[name="estimateDetail.estimationItems[${index}].threePointEstimate"]`).val(Math.round(calculatedThreePointEstimate));
			}
			
			// If we have a valid three-point estimate, call backend
	        if (calculatedThreePointEstimate > 0) {
	            const classification = $(`input[name="estimateDetail.estimationItems[${index}].classification"]`).val();

	            if (classification) {
	                fetchCutOffParameters(classification).done(function (params) {
	                        // if backend returns plain number
	                        let pct = parseFloat(params.cutPercentage) || 0;
							let pmp = parseFloat(params.projectManagerPercentage) || 0;
							let bap = parseFloat(params.businessAnalystPercentage) || 0;
							let dvp = parseFloat(params.developmentPercentage) || 0;
							let qap = parseFloat(params.qualityAssurancePercentage) || 0;
							let aap = parseFloat(params.assetsAndAcceleratorPercentage) || 0;
							let gap = parseFloat(params.genAIPercentage) || 0;
							if(pct > 0){
		                        console.log("Classification:", classification);
								console.log("cutPercentage:", pct);
								console.log("projectManagerPercentage:", pmp);
								console.log("businessAnalystPercentage:", bap);
								console.log("developmentPercentage:", dvp);
								console.log("qualityAssurancePercentage:", qap);
								console.log("assetsAndAcceleratorPercentage:", aap);
								console.log("genAIPercentage:", gap);
								
								let effortInPersonHours = calculatedThreePointEstimate/ pct;
								let projectManagementEffort = effortInPersonHours * pmp;
								let businessAnalysisEffort = effortInPersonHours * bap;
								let developmentEffort = effortInPersonHours * dvp;
								let qualityAssuranceEffort = effortInPersonHours * qap;
								let assetsAndAcceleratorsEffort = effortInPersonHours * aap;
								let genAIEffort = effortInPersonHours * gap;
								
								let personHours = projectManagementEffort + businessAnalysisEffort + developmentEffort + qualityAssuranceEffort;
								let personDays = personHours / 8;
								let personMonths = personHours / 160;
	                            let adjusted = projectManagementEffort + businessAnalysisEffort + developmentEffort + qualityAssuranceEffort + assetsAndAcceleratorsEffort + genAIEffort;
								alert("Adjusted Effort is : "+adjusted);
		                        $(`input[name="estimateDetail.estimationItems[${index}].effortInPersonHours"]`).val(Math.round(effortInPersonHours));
								$(`input[name="estimateDetail.estimationItems[${index}].projectManagementEffort"]`).val(Math.round(projectManagementEffort));
								$(`input[name="estimateDetail.estimationItems[${index}].businessAnalysisEffort"]`).val(Math.round(businessAnalysisEffort));
								$(`input[name="estimateDetail.estimationItems[${index}].developmentEffort"]`).val(Math.round(developmentEffort));
								$(`input[name="estimateDetail.estimationItems[${index}].qualityAssuranceEffort"]`).val(Math.round(qualityAssuranceEffort));
								$(`input[name="estimateDetail.estimationItems[${index}].assetsAndAcceleratorsEffort"]`).val(Math.round(assetsAndAcceleratorsEffort));
								$(`input[name="estimateDetail.estimationItems[${index}].genAIEffort"]`).val(Math.round(genAIEffort));
								
								$(`input[name="estimateDetail.estimationItems[${index}].personHours"]`).val(Math.round(personHours));
								$(`input[name="estimateDetail.estimationItems[${index}].personDays"]`).val(Math.round(personDays));
								$(`input[name="estimateDetail.estimationItems[${index}].personMonths"]`).val(Math.round(personMonths));
							}
							recomputeVerticalTotals();

                    }).fail(function (xhr, status, error) {
	                        console.error("Error fetching percentage:", status, error);
	                        safeShowToast && safeShowToast('Error fetching percentage: ' + (error || ''), 'danger');
                    });
	            } else {
					reComputeVerticalTotals();
				}
	        }else{
				recomputeVerticalTotals();
			}
        } catch (e) {
            console.error('recalculateItems exception', e);
            safeShowToast && safeShowToast('Error Reclaculating items: ' + (e && e.message ? e.message : ''), 'danger');
        }
	}
	
	// Recompute vertical totals across all estimation items
	function recomputeVerticalTotals(){
		try{
			let totalEffort = 0;
			let totalPM = 0;
			let totalBA = 0;
			let totalDev = 0;
			let totalQA = 0;
			let totalAAA = 0;
			let totalGenAI = 0;
			let totalPersonHours = 0;
			let totalPersonDays = 0;
			let totalPersonMonths = 0;
			
			totalPM = sumColumn("projectManagementEffort");
			totalBA = sumColumn("businessAnalysisEffort");
			totalDev = sumColumn("developmentEffort");
			totalQA = sumColumn("qualityAssuranceEffort");
			totalAAA = sumColumn("assetsAndAcceleratorsEffort");
			totalGenAI = sumColumn("genAIEffort");
			totalPersonHours = sumColumn("personHours");
			totalPersonDays = sumColumn("personDays");
			totalPersonMonths = sumColumn("personMonths");
			
			totalEffort = totalPM + totalBA + totalDev + totalQA + totalAAA + totalGenAI;
			console.log("Total Effort:", totalEffort);
			console.log("Total PM:", totalPM);
			console.log("Total BA:", totalBA);
			console.log("Total Dev:", totalDev);
			console.log("Total QA:", totalQA);
			console.log("Total AAA:", totalAAA);
			console.log("Total GenAI:", totalGenAI);
			console.log("Total Person Hours:", totalPersonHours);
			console.log("Total Person Days:", totalPersonDays);
			console.log("Total Person Months:", totalPersonMonths);
			
			
			$(`input[name="estimateDetail.totalEffortInPersonHours"]`).val(totalEffort);
			$(`input[name="estimateDetail.totalProjectManagementEffort"]`).val(totalPM);
			$(`input[name="estimateDetail.totalBusinessAnalysisEffort"]`).val(totalBA);
			$(`input[name="estimateDetail.totalDevelopmentEffort"]`).val(totalDev);
			$(`input[name="estimateDetail.totalQualityAssuranceEffort"]`).val(totalQA);
			$(`input[name="estimateDetail.totalAssetsAndAcceleratorsEffort"]`).val(totalAAA);
			$(`input[name="estimateDetail.totalGenAIEffort"]`).val(totalGenAI);
			$(`input[name="estimateDetail.totalPersonHours"]`).val(totalPersonHours);
			$(`input[name="estimateDetail.totalPersonDays"]`).val(totalPersonDays);
			$(`input[name="estimateDetail.totalPersonMonths"]`).val(totalPersonMonths);
		}catch(e){
			console.error('recomputeVerticalTotals exception', e);
			safeShowToast && safeShowToast('Error recomputing vertical totals: ' + (e && e.message ? e.message : ''), 'danger');
		}
	}
	
	// Sum up a specific column across all estimation items
	function sumColumn(fieldName) {
	    let total = 0;
		try{
			const $inputs = $(`input[name^="estimateDetail.estimationItems"][name$=".${fieldName}"]`);
			console.log(`sumColumn("${fieldName}") found`, $inputs.length, "inputs");
		    $inputs.each(function () {
		        const raw = $(this).val();
		        const val = parseFloat(raw) || 0;
		        console.log("  ->", $(this).attr("name"), "raw:", raw, "parsed:", val);
		        total += val;
		    });

			console.log(`  ==> total for ${fieldName} =`, total);
        }catch(e){
			console.error('_sumColumnInternal exception', e);
			safeShowToast && safeShowToast('Error summin up column : '+fieldName + (e && e.message ? e.message : ''), 'danger');
		}
		
	    return Math.round(total);
	}
	
	// Fetch cut-off parameters from server based on classification
	function fetchCutOffParameters(classification) {
		var $form = $('#teaEstimateForm');
		// Derive base controller path from the form action (expected to end with /save or /save-ajax)
        var formAction = $form.attr('action') || window.location.pathname;
        var base = formAction.replace(/\/save(?:-ajax)?$/,'');
        var url = base + '/cutoff-parameters';
		/*var url = '/admin/tea-estimate/cutoff-parameters';*/
	    return $.ajax({
	        url: url,  // Your controller URL
	        type: 'GET',
	        dataType: 'json',                       // IMPORTANT: tells jQuery to parse JSON
	        data: { classification: classification }
	    });
	}

	// Toast notification: default no-op implementation (JSP may provide real one)
    function safeShowToast(message, type, timeoutMs) { 
        console.log('Toast message:', message, 'type:', type, 'timeoutMs:', timeoutMs);
	}
    try { 
		window.safeShowToast = safeShowToast; 
	} catch (e) { 
		console.error('Error exposing safeShowToast', e);
	}

    // Add Estimation Item: implemented to call server endpoint and replace estimate details fragment
    // This function is intentionally exposed for direct calls from inline onClick (backward compatible)
    window.addEstimationItem = function() {
        // delegate to the shared handler to keep logic in one place
        _doAddEstimationItem();
    };
	
	window.removeEstimationItem = function(id) {
		_doRemoveEstimationItem(id);
	};
	
	

    // Internal handler for removing estimation item
	function _doRemoveEstimationItem(itemIndex) {
		try {
			alert("inside remove function with index : "+itemIndex);
            var $form = $('#teaEstimateForm');
			// Derive base controller path from the form action (expected to end with /save or /save-ajax)
            var formAction = $form.attr('action') || window.location.pathname;
            var base = formAction.replace(/\/save(?:-ajax)?$/,'');
            var url = base + '/remove-item?index='+itemIndex;
			alert("final URL is : "+url);
			$("#teaEstimateForm").attr("action", url);
			$("#teaEstimateForm").submit();
					
        } catch (e) {
            console.error('addEstimationItem exception', e);
            safeShowToast && safeShowToast('Error adding item: ' + (e && e.message ? e.message : ''), 'danger');
        }
	}
	
	window.removeResourceInfo = function(id) {
			_doRemoveResourceInfo(id);
	};
	
	// Internal handler for removing estimation item
	function _doRemoveResourceInfo(itemIndex) {
		try {
			alert("inside remove resource info function with index : "+itemIndex);
            var $form = $('#teaEstimateForm');
			// Derive base controller path from the form action (expected to end with /save or /save-ajax)
            var formAction = $form.attr('action') || window.location.pathname;
            var base = formAction.replace(/\/save(?:-ajax)?$/,'');
            var url = base + '/remove-resource?index='+itemIndex;
			alert("final URL is : "+url);
			$("#teaEstimateForm").attr("action", url);
			$("#teaEstimateForm").submit();
					
        } catch (e) {
            console.error('addEstimationItem exception', e);
            safeShowToast && safeShowToast('Error adding item: ' + (e && e.message ? e.message : ''), 'danger');
        }
	}
	
	// Internal handler for adding estimation item
    function _doAddEstimationItem() {
        try {
            var $form = $('#teaEstimateForm');
			alert($form.length);
            var formAction = $form.attr('action') || window.location.pathname;
            var base = formAction.replace(/\/save(?:-ajax)?$/,'');
            var url = base + '/add-item';
			alert("final URL is : "+url);
			$("#teaEstimateForm").attr("action", url);
			$("#teaEstimateForm").submit();
        } catch (e) {
            console.error('addEstimationItem exception', e);
            safeShowToast && safeShowToast('Error adding item: ' + (e && e.message ? e.message : ''), 'danger');
        }
    }
	
	// Internal handler for adding estimation item
	    function _doAddResourceInfo() {
	        try {
	            var $form = $('#teaEstimateForm');
				alert($form.length);
	            var formAction = $form.attr('action') || window.location.pathname;
	            var base = formAction.replace(/\/save(?:-ajax)?$/,'');
	            var url = base + '/add-resource';
				alert("final URL is : "+url);
				$("#teaEstimateForm").attr("action", url);
				$("#teaEstimateForm").submit();
	        } catch (e) {
	            console.error('addEstimationItem exception', e);
	            safeShowToast && safeShowToast('Error adding item: ' + (e && e.message ? e.message : ''), 'danger');
	        }
	    }

    // DOM ready hook (attach delegated handler)
    $(function(){
        // Delegate clicks so newly loaded fragments' buttons work too
        $(document).on('click', '.js-add-estimation-item', function(e){
            e.preventDefault();
            _doAddEstimationItem();
        });
        /* intentionally empty additional init */
		$(document).on('click', '.js-add-resoure-info', function(e){
            e.preventDefault();
            _doAddResourceInfo();
        });
    });

})(jQuery);