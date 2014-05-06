<%-- 
    Document   : myBooks
    Created on : 06-May-2014, 12:32:46
    Author     : Erik
--%>
<%@taglib prefix="t" uri="/WEB-INF/tlds/slls" %>
<%@page contentType="text/xml" pageEncoding="UTF-8"%>
<!DOCTYPE messageResponse [
    <!ELEMENT messageResponse (book+)>
    <!ELEMENT book (isbn,title,author,year)>
    <!ELEMENT isbn (#PCDATA)>
    <!ELEMENT title (#PCDATA)>
    <!ELEMENT author (#PCDATA)>
    <!ELEMENT year (#PCDATA)>
    ]>
    <messageResponse>
        <t:GenerateTable list="${requestScope.myLentBooksList}">
            <book>
                <isbn>
                    ${isbn}
                </isbn>
                <title>
                    ${title}
                </title>
                <author>
                    ${author}
                </author>
                <year>
                    ${pub_year}
                </year>
            </book>
        </t:GenerateTable>
    </messageResponse>
