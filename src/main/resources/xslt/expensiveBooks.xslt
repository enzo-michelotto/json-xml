<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <xsl:element name="mr:result" xmlns:mr="http://www.example.com/xquery">

            <xsl:variable name="average">
                <xsl:value-of select="sum(//price) div count(//price)"/>
            </xsl:variable>

            <mr:average_price>
                <xsl:value-of select="$average"/>
            </mr:average_price>

            <xsl:for-each select="/library/publisher/book">

                <xsl:variable name="price">
                    <xsl:value-of select="price"/>
                </xsl:variable>

                <xsl:variable name="price_difference">
                    <xsl:value-of select="$price - $average"/>
                </xsl:variable>

                <xsl:if test="$price_difference>0">
                <mr:expensive_book>
                    <mr:title>
                        <xsl:value-of select="title"/>
                    </mr:title>
                    <mr:current_price>
                        <xsl:value-of select="$price"/>
                    </mr:current_price>
                    <mr:price_difference>
                        <xsl:value-of select="$price_difference"/>
                    </mr:price_difference>
                </mr:expensive_book>
                </xsl:if>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>