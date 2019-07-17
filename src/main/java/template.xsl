<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">
	<xsl:output encoding="UTF-8" indent="yes" method="xml"
		standalone="no" omit-xml-declaration="no" />
	<xsl:template match="salary-info">
		<fo:root language="EN">
			<fo:layout-master-set>
				<fo:simple-page-master
					master-name="A4-portrail" page-height="297mm" page-width="210mm"
					margin-top="5mm" margin-bottom="5mm" margin-left="5mm"
					margin-right="5mm">
					<fo:region-body margin-top="25mm"
						margin-bottom="20mm" />
					<fo:region-before region-name="xsl-region-before"
						extent="25mm" display-align="before" precedence="true" />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="A4-portrail">
				<fo:static-content flow-name="xsl-region-before">
					<fo:table border-width="0.4%" border-color="black"
						font-size="10pt" width="100%" table-layout="fixed">
						<fo:table-column
							column-width="proportional-column-width(45)" />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell display-align="left"
									text-align="left">
									<fo:block>
										<fo:external-graphic width="25%"
											height="25%" content-width="scale-to-fit"
											content-height="scale-to-fit" scaling="uniform"
											src="src//main//resources//logo.gif" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>

				<fo:flow flow-name="xsl-region-body"
					border-collapse="collapse" reference-orientation="0">
					<fo:block border-bottom-style="solid">
						<fo:leader />
					</fo:block>
					<fo:table width="100%" margin-top="5%" font-size="9pt">
						<fo:table-column
							column-width="proportional-column-width(20)" />
						<fo:table-column
							column-width="proportional-column-width(30)" />
						<fo:table-column
							column-width="proportional-column-width(20)" />
						<fo:table-column
							column-width="proportional-column-width(30)" />
						<fo:table-body>
							<fo:table-row height="8mm">
								<fo:table-cell>
									<fo:block>EMP NO</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										:
										<xsl:value-of select="employee-id" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>EMP DOJ</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										:
										<xsl:value-of select="employee-joining-date" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="8mm">
								<fo:table-cell>
									<fo:block>NAME</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										:
										<xsl:value-of select="employee-name" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>EMP PAN</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										:
										<xsl:value-of select="pan-number" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="8mm">
								<fo:table-cell>
									<fo:block>LOCATION</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										:
										<xsl:value-of select="location" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>GENDER</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										:
										<xsl:value-of select="gender" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="8mm">
								<fo:table-cell>
									<fo:block>DESG </fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										:
										<xsl:value-of select="designation" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>UAN</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										:
										<xsl:value-of select="uan-number" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="8mm">
								<fo:table-cell>
									<fo:block>PF NO</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										:
										<xsl:value-of select="pf-number" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>DAYS WORKED</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										:
										<xsl:value-of select="number-of-days-worked" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="8mm">
								<fo:table-cell>
									<fo:block>A/C NO </fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										:
										<xsl:value-of select="account-number" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>LWP DAYS</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										:
										<xsl:value-of select="loss-of-pay-days" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="8mm">
								<fo:table-cell>
									<fo:block>BANK NAME </fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										:
										<xsl:value-of select="bank-name" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>

					<fo:block border-bottom-style="solid">
						<fo:leader />
					</fo:block>
					<fo:table width="100%" margin-top="5%" font-size="9pt">
						<fo:table-column
							column-width="proportional-column-width(10%)" />
						<fo:table-column
							column-width="proportional-column-width(6%)" />
						<fo:table-column
							column-width="proportional-column-width(19%)" />
						<fo:table-column
							column-width="proportional-column-width(14%)" />
						<fo:table-column
							column-width="proportional-column-width(14%)" />
						<fo:table-column
							column-width="proportional-column-width(14%)" />
						<fo:table-column
							column-width="proportional-column-width(15%)" />
						<fo:table-body>
							<fo:table-row height="8mm">
								<fo:table-cell>
									<fo:block>EARNINGS</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>RATE</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>CURRENT MONTH</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>ARREAR(+/-)</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>GROSS</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>DEDUCTIONS</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block>CURRENT MONTH</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
					<fo:block border-bottom-style="solid">
						<fo:leader />
					</fo:block>
					<fo:table width="100%" margin-top="5%" font-size="9pt">
						<fo:table-column
							column-width="proportional-column-width(10%)" />
						<fo:table-column
							column-width="proportional-column-width(6%)" />
						<fo:table-column
							column-width="proportional-column-width(19%)" />
						<fo:table-column
							column-width="proportional-column-width(14%)" />
						<fo:table-column
							column-width="proportional-column-width(14%)" />
						<fo:table-column
							column-width="proportional-column-width(14%)" />
						<fo:table-column
							column-width="proportional-column-width(15%)" />
						<fo:table-body>
							<fo:table-row height="8mm">
								<fo:table-cell text-align="left">
									<fo:block>BASIC</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="basic" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="basic" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>

										<xsl:value-of select="basic-arrear" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="basic-gross" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>P.F.</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="provident-fund" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="8mm">
								<fo:table-cell text-align="left">
									<fo:block>SPECIAL ALLOW</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="special-allowance" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="special-allowance" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="special-allowance-arrear" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="special-allowance-gross" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>PROFFESSION TAX</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="profession-tax" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row height="8mm">
								<fo:table-cell text-align="left">
									<fo:block>SIGN ON BONUS</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="sign-onbonus-rate" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="sign-onBonus-currentmonth" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="sign-onbonus-arrear" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="sign-onbonus-gross" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
					<fo:block border-bottom-style="solid">
						<fo:leader />
					</fo:block>
					<fo:table width="100%" margin-top="5%" font-size="9pt">
						<fo:table-column
							column-width="proportional-column-width(10%)" />
						<fo:table-column
							column-width="proportional-column-width(6%)" />
						<fo:table-column
							column-width="proportional-column-width(19%)" />
						<fo:table-column
							column-width="proportional-column-width(14%)" />
						<fo:table-column
							column-width="proportional-column-width(14%)" />
						<fo:table-column
							column-width="proportional-column-width(14%)" />
						<fo:table-column
							column-width="proportional-column-width(15%)" />
						<fo:table-body>
							<fo:table-row height="8mm">
								<fo:table-cell>
									<fo:block>GROSS EARNINGS</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<!-- <xsl:value-of select="" /> -->
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="gross-earning-month" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="gross-earning-arrear" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="gross-earning-total" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>TOTAL DEDUCTIONS</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="total-deductions" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
					<fo:block border-bottom-style="solid">
						<fo:leader />
					</fo:block>
					<fo:table width="100%" margin-top="5%" font-size="9pt">
						<fo:table-column
							column-width="proportional-column-width(10%)" />
						<fo:table-column
							column-width="proportional-column-width(6%)" />
						<fo:table-column
							column-width="proportional-column-width(19%)" />
						<fo:table-column
							column-width="proportional-column-width(14%)" />
						<fo:table-column
							column-width="proportional-column-width(14%)" />
						<fo:table-column
							column-width="proportional-column-width(14%)" />
						<fo:table-column
							column-width="proportional-column-width(15%)" />
						<fo:table-body>
							<fo:table-row height="8mm">
								<fo:table-cell>
									<fo:block>NET PAY -</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block>
										<xsl:value-of select="net-pay" />
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block></fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block></fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block></fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block></fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block></fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
					<fo:block border-bottom-style="solid">
						<fo:leader />
					</fo:block>
					<fo:table space-after="5%" text-align="center"
						width="100%" font-size="10pt" display-align="center"
						margin-top="2%">
						<fo:table-body>
							<fo:table-row height="5mm">
								<fo:table-cell>
									<fo:block text-align="center" display-align="center"
										space-before="5%">
										<xsl:text disable-output-escaping="no">THIS IS A COMPUTER GENERATED STATEMENT AND DOES NOT REQUIRE SIGNATURE OR STAMP</xsl:text>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>

					<fo:block border-bottom-style="solid">
						<fo:leader />
					</fo:block>

				</fo:flow>
			</fo:page-sequence>

		</fo:root>
	</xsl:template>
</xsl:stylesheet>
