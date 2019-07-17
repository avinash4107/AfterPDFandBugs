<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">
	<xsl:output encoding="UTF-8" indent="yes" method="xml"
		standalone="no" omit-xml-declaration="no" />
	<xsl:template match="salary-info">
		<fo:root language="EN">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="page"
					page-height="11in" page-width="8.5in">
					<fo:region-body margin="1in"
						background-color="yellow" border="solid thick orange" />
					<fo:region-before extent="1in"
						background-color="lightblue" border="solid thick blue" />
					<fo:region-after extent="1in"
						background-color="lightblue" border="solid thick blue" />
					<fo:region-start extent="1in"
						background-color="lightgreen" border="solid thick green" />
					<fo:region-end extent="1in"
						background-color="lightgreen" border="solid thick green" />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="page"
				font-size="24pt" font-weight="bold" text-align="center">
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>
