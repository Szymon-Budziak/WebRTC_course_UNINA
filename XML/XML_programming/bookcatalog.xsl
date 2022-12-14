<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="BookCatalog">
        <html>
            <head>
                <title>Simple Web Catalog</title>
            </head>
            <body>
                <h1>Book Catalog</h1>
                <xsl:apply-templates select="Book"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="Book">
        Title:
        <xsl:value-of select="Title"/>
        <br/>
        Author:
        <xsl:value-of select="Author"/>
        <br/>
        Editor:
        <xsl:value-of select="Editor"/>
        <br/>
        <br/>
    </xsl:template>
</xsl:stylesheet>