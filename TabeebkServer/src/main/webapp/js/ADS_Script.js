$(document).ready(function () {
    $('#example').dataTable();
});
$(document).ready(function () {
    $('#example2').dataTable();
});
function CancelFunction(url) {
    window.open(url, '_self');
}
function addRowHandlers(url) {
    var table = document.getElementById("example");
    var rows = table.getElementsByTagName("tr");
    for (i = 0; i < rows.length; i++) {
        var currentRow = table.rows[i];
        var createClickHandler =
                function (row)
                {
                    return function () {
                        var id = row.getAttribute('id');
//                        alert("id:" + id);
                        if (id != null) {
                            window.open(url + "?id=" + id, '_self');
                        }
                    };
                };

        currentRow.onclick = createClickHandler(currentRow);
    }
}

function EditPlan(url,id) {
    document.location.href = url+"?id=" + id;
}
function AddMspToPlan(url,planId,mspTypeId,typeId) {
    document.location.href = url+"?planid=" + planId+"&msptypeid="+mspTypeId+"&mspid="+typeId;
}
//function RemovePlan(url,planid,mspid) {
//    document.location.href = url+"?planid=" + planid+"&mspid="+mspid;
//}