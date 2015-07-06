<html>
	<head>
		<link href="stylesheet.css" rel="stylesheet" type="text/css" title="Style">
		<title>${(reportHeader)!"N/A"}</title>
		<h1>${(reportHeader)!"N/A"}</h1>
	</head>
	<body>
		<div class="home">
			<a href='index.html'>Home</a>
		</div>
		<h2>${(moduleHeader)!"N/A"}</h2>
		<table class="details">
			<tr>
				<th width="35%"> Test scenario </th>
				<!-- th width="12%"> URL </th -->
				<th width="4%"> Format </th>
				<th width="4%"> Status </th>
				<th width="17%"> Request Param </th>
				<th width="40%"> Description </th>
			</tr>
			<#list testResults as result>
			<#if result.status == "FAIL">
				<tr class="Failure">
			<#elseif result.status == "ERROR">
				<tr class="Error">
			<#else>
				<tr>
			</#if>				
					<td>
						<p>${(result.testName)!"N/A"}</p>
						<p><B><I>URL : </I><B><a href='${(result.url)}'> ${(result.url)!"N/A"}</a></p>
					</td>					
					<!-- td><a href='${(result.url)}'> ${(result.url)!"N/A"}</a></td -->
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
						<#if result.requestParams?size == 1 >
							<#list result.requestParams?keys as key>
									${key}=${result.requestParams[key]}
							</#list>
						<#else>
							N/A
						</#if>
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