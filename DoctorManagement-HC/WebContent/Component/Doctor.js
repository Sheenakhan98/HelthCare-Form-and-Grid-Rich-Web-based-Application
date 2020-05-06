
$(document).ready(function()
{
	if ($("#alertSuccess").text().trim()==""){
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();

});


// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
		// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
	
		// Form validation-------------------
		var status = validateDocForm();
		if (status != true)
		{
			$("#alertError").text(status);
			$("#alertError").show();
			return;
		}
		
		// If valid------------------------
		var type = ($("#hidDocIDSave").val() == "") ? "POST" : "PUT";
		$.ajax(
		{
			url : "DoctorsAPI",
			type : type,
			data : $("#doctorForm").serialize(),
			dataType : "text",
			complete : function(response, status)
			{
				onItemSaveComplete(response.responseText, status);
			}
		});
});
		
	
	
function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divDocGrid").load(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
		$("#hidDocIDSave").val("");
		$("#doctorForm")[0].reset();
	}
	
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "DoctorsAPI",
		type : "DELETE",
		data : "docId=" + $(this).data("docid"),
		dataType : "text",
		complete : function(response, status)
		{
			onItemDeleteComplete(response.responseText, status);
		}
	});
});
	
function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully deleted.");
		$("#alertSuccess").show();
		
		$("#divDocGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidDocIDSave").val($(this).closest("tr").find('#hidDocIDUpdate').val());
$("#FirstName").val($(this).closest("tr").find('td:eq(0)').text());
$("#LastName").val($(this).closest("tr").find('td:eq(1)').text());
$("#Specialization").val($(this).closest("tr").find('td:eq(2)').text());
$("#Gender").val($(this).closest("tr").find('td:eq(3)').text());
$("#Email").val($(this).closest("tr").find('td:eq(4)').text());
$("#ContactNo").val($(this).closest("tr").find('td:eq(5)').text());
$("#StandardFees").val($(this).closest("tr").find('td:eq(6)').text());
$("#Password").val($(this).closest("tr").find('td:eq(7)').text());
});

// CLIENTMODEL=========================================================================
function validateDocForm()
{
	// FirstName
	if ($("#FirstName").val().trim() == "")
	{
		return "Insert First Name.";
	}
	// LastName
	if ($("#LastName").val().trim() == "")
	{
		return "Insert Last Name.";
	}
	//Specialization
	if ($("#Specialization").val().trim() == "")
	{
		return "Insert Specialization.";
	}
	//Gender
	if ($("#Gender").val().trim() == "")
	{
		return "Insert Gender.";
	}
	//Email
	if ($("#Email").val().trim() == "")
	{
		return "Insert Email.";
	}
	//Contact
	if ($("#ContactNo").val().trim() == "")
	{
		return "Insert Contact No.";
	}
	// Fees-------------------------------
	if ($("#StandardFees").val().trim() == "")
	{
		return "Insert Standard Fees.";
	}
	// is numerical value
	var tmpFees = $("#StandardFees").val().trim();
	if (!$.isNumeric(tmpFees))
	{
		return "Insert a numerical value for Standard Fees.";
	}
	// convert to decimal price
		$("#StandardFees").val(parseFloat(tmpFees).toFixed(2));
	// Password------------------------
	if ($("#Password").val().trim() == "")
	{
		return "Insert Password.";
	}
	return true;
}