<html>
	<head>
		<link href="stylesheet.css" rel="stylesheet" type="text/css" title="Style">
		<title>${(reportHeader)!"N/A"}</title>
		
	</head>
	<body>
		<h1>${(reportHeader)!"N/A"}</h1>
		<h2>Overall summary</h2>
		<table class="details" width="100%">
			<tr>
				<th> Total </th>
				<th> PASS </th>
				<th> FAIL </th>
				<th> ERROR </th>
			</tr>
			<tr>
				<td>${(overallSummary.totalTests)!"N/A"}</td>
				<td>${(overallSummary.passTests)!"N/A"}</td>
				<td>${(overallSummary.failTests)!"N/A"}</td>
				<td>${(overallSummary.errorTests)!"N/A"}</td>
			</tr>
		</table>
		
		<h2>Module summary</h2>
		<table class="details" width="100%">
			<tr>
				<th> Module </th>
				<th> Total </th>
				<th> PASS </th>
				<th> FAIL </th>
				<th> ERROR </th>
			</tr>
			<#list moduleSummary as modSummary>
				<tr>
					<td><a href='${modSummary.module}.html'> ${(modSummary.module)!"N/A"}</a></td>
					<td>${(modSummary.totalTests)!"N/A"}</td>
					<td>${(modSummary.passTests)!"N/A"}</td>
					<td>${(modSummary.failTests)!"N/A"}</td>
					<td>${(modSummary.errorTests)!"N/A"}</td>
				</tr>
			</#list>
		</table>
		
	</body>
</html>