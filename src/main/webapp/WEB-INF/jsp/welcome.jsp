<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Logs Report</title>
<style type="text/css">
body {
	background-image: url('<c:url value="/resources/css/bg.png" />');
}
</style>
<link href="<c:url value="/resources/css/shCore.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/demo.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/dataTables.jquery-ui.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/jquery-ui.css" />"	rel="stylesheet">
<script type="text/javascript" src="<c:url value="/resources/js/jquery1.11.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/columnFilter.js" />"></script>
<script type="text/javascript">
	$(document).ready( function () {
		
		
		
		$('#menuTable1 tbody').on('mouseover', 'tr', function()
		{
			$('#menuTable1 tbody tr').css("background-color", "#F9F9F9");
			$(this).css("background-color", "#D8D8D8");
		}).on('mouseleave', function()
		{
			$('#menuTable1 tbody tr').css("background-color", "#F9F9F9");
			$(this).css("background-color", "#F9F9F9");
		});
		
		$("td.desc").each(function(){
		    var val = $(this).text();

		    if(val.length > 70){
		        $(this).attr("title", val);
		        $(this).text( clip( $(this).text() ) );
		    }
		});

		function clip(string){        
		    return string.substr(0, 70) + "...";        
		}
		
		var tableOptions =
	    {
		  "dom" : '<"H"lfr>t<"F"ipfr>',
	      "scrollY" : 400,
	      "scrollCollapse" : true,
	      "paging" : true,
	      "rowHeight" : 'auto',
	      "pageLength" : 10,
	      "jQueryUI" : true,
	      "columnDefs": [
	                     {
	                         "targets": [ 3 ],
	                         "visible": false,
	                         "searchable": true
	                     },
	                 ]
	    };
		var table;
		try
	    {
	        table = $('#menuTable1').dataTable(tableOptions);
			$('.dataTables_scrollHead').css("overflow", "hidden");
			$('.dataTables_scrollBody').scroll(function()
			{
				$('.dataTables_scrollHead').scrollLeft($(this).scrollLeft());
			});
	        $(".dataTables_filter").focus();
		    $($(".dataTables_filter").find("input")[0]).val("a");
		    $($(".dataTables_filter").find("input")[0]).keyup();			
		    $($(".dataTables_filter").find("input")[0]).val(""); 
		    
		    function filterGlobal () {
		    	$('#menuTable1').DataTable().search(
			        $('#global_filter').val(),
			        $('#global_regex').prop('checked'),
			        $('#global_smart').prop('checked'),true
			    ).draw();
			}
			 
			function filterColumn ( i ) {
							//alert(i);
					$('#menuTable1').DataTable().column( i ).search(
			        $('#col'+i+'_filter').val(),
			        $('#col'+i+'_regex').prop('checked'),
			        $('#col'+i+'_smart').prop('checked'),true
			    ).draw();
			}
		    
		    $('input.global_filter').on( 'keyup click', function () {
		    			//alert("aa");
		        filterGlobal();
		    } );
		 
		    $('input.column_filter').on( 'keyup click', function () {
		    	//alert($(this).parents('tr').attr('data-column'));
		        filterColumn( $(this).parents('td').attr('data-column') );
		    } );
		    //alert("ssd");
	    }
		catch(e)
		{
			//alert(e);
		}
});
	</script>
</head>
<body>
	<br>
	<div style="text-align: center">
		<h2 style="font-family: DejaVu Serif; font-weight: bolder;">
			Visual Log analyzer<br> <br>
		</h2>
		<c:if test="${not empty allLogList}">

			<table border="1" id="menuTable1">
				<thead>
					<tr>
						<td align="center" data-column="0"><input class="column_filter"
						id="col0_filter" type="text" placeholder="Event Date"></td>
						<td align="center" data-column="1"><input class="column_filter"
						id="col1_filter" type="text" placeholder="Severity"></td>
						<td align="center" data-column="2"><input class="column_filter"
						id="col2_filter" type="text" placeholder="Event Producer"></td>
						<td align="center" data-column="3"><input class="column_filter"
						id="col3_filter" type="text" placeholder="Message"></td>
					</tr>
					<tr>
						<th>Event Date</th>
						<th>Severity</th>
						<th>Event Producer</th>
						<th>HideMessage</th>
						<th>Message</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="allLog" items="${allLogList}">
						<tr>
							<td><c:out value="${allLog.date}" /></td>
							<td><c:out value="${allLog.priority}" /></td>
							<td><c:out value="${allLog.javaClassName}" /></td>
							<td><c:out value="${allLog.description}" /></td>
							<td class="desc"><c:out value="${allLog.description}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</c:if>
	</div>
</body>
</html>