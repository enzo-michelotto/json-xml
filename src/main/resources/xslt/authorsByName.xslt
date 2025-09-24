<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

    <xsl:param name="author_name"/>
    <xsl:template match="/">
        <xsl:element name="mr:author_list" xmlns:mr="http://www.example.com/xquery">
            <xsl:for-each select="library/publisher/book[author=$author_name]">
                <mr:author>
                    <mr:name>
                        <xsl:value-of select="author" />
                    </mr:name>
                    <mr:title>
                        <xsl:value-of select="title"/>
                    </mr:title>
                </mr:author>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
