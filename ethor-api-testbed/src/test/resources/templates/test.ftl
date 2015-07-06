<html>
	<head>
		<link href="stylesheet.css" rel="stylesheet" type="text/css" title="Style">
		<title>${(reportData.title)!"N/A"}</title>
		
	</head>
	<body>
		<h1>${(reportData.reportHeader)!"N/A"}</h1>
		<table class="details" width="100%">
			<tr>
				<th> Test name </th>
				<th> Format </th>
				<th> Status </th>
				<th> Request Param </th>
				<th> Description </th>
			</tr>
			<#list reportData.results as result>
			<#if result.status == "FAIL">
				<tr class="Failure">
			<#elseif result.status == "ERROR">
				<tr class="Error">
			<#else>
				<tr>
			</#if>				
					<td>${(result.testName)!"N/A"}</td>
					<td>${result.format!"N/A"}</td>
					<td>${result.status!"N/A"}</td>
					<td>
					<#if result.requestParams?size &gt; 1 >
						<ul>						
							<#list result.requestParams?keys as key>
								<li>${key}=${result.requestParams[key]}</li>
							</#list>
						</ul>
					<#else>
						<#list result.requestParams?keys as key>
								${key}=${result.requestParams[key]}
						</#list>
					</#if>
					
					</td>
					
					
					<td>
					<#if result.testDetails?size &gt; 1 >
						<ul>
						<#list result.testDetails as comment>
							<li>${comment!"N/A"}</li>
						</#list>
						</ul>
					<#else>
					<#list result.testDetails as comment>
							${comment!"N/A"}
						</#list>
					</#if>
					</td>
				</tr>
			</#list>
		</table>
	</body>
</html>