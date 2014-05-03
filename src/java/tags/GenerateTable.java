/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tags;

import active_record.BookActiveRecord;
import active_record.DatabaseUtility;
import active_record.RegisteredUserActiveRecord;
import java.util.ArrayList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 *
 * @author Erik
 */
public class GenerateTable extends BodyTagSupport {

    private ArrayList<DatabaseUtility> list;
    private int counter = 0;

    /**
     *
     * @param e
     */
    private void evaluateActiveRecord(DatabaseUtility e) {
        if (e instanceof RegisteredUserActiveRecord) {
            this.pageContext.setAttribute("user_id", ((RegisteredUserActiveRecord) e).getUser_id());
            this.pageContext.setAttribute("first_name", ((RegisteredUserActiveRecord) e).getFirst_name());
            this.pageContext.setAttribute("last_name", ((RegisteredUserActiveRecord) e).getLast_name());
            this.pageContext.setAttribute("total_fines",  ((RegisteredUserActiveRecord) e).getTotal_fines());
            this.pageContext.setAttribute("user_type",  ((RegisteredUserActiveRecord) e).getUser_type());
        } else if (e instanceof BookActiveRecord) {
            this.pageContext.setAttribute("isbn", ((BookActiveRecord) e).getIsbn());
            this.pageContext.setAttribute("title", ((BookActiveRecord) e).getTitle());
            this.pageContext.setAttribute("pub_year", ((BookActiveRecord) e).getYear());
            this.pageContext.setAttribute("status", ((BookActiveRecord) e).getStatus());
            this.pageContext.setAttribute("author", ((BookActiveRecord) e).getAuthor());
        }
    }

    /**
     * This method is called when the JSP engine encounters the start tag, after
     * the attributes are processed. Scripting variables (if any) have their
     * values set here.
     *
     * @return EVAL_BODY_BUFFERED if the JSP engine should evaluate the tag
     * body, otherwise return SKIP_BODY. This method is automatically generated.
     * Do not modify this method. Instead, modify the methods that this method
     * calls.
     */
    @Override
    public int doStartTag() throws JspException {
        if (list != null && !list.isEmpty()) {
            evaluateActiveRecord(list.get(counter));
            counter++;
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }

    }

    /**
     * This method is called after the JSP engine finished processing the tag.
     *
     * @return EVAL_PAGE if the JSP engine should continue evaluating the JSP
     * page, otherwise return SKIP_PAGE. This method is automatically generated.
     * Do not modify this method. Instead, modify the methods that this method
     * calls.
     */
    @Override
    public int doEndTag() throws JspException {
        counter = 0;
        return EVAL_PAGE;
    }

    /**
     * This method is called after the JSP engine processes the body content of
     * the tag.
     *
     * @return EVAL_BODY_AGAIN if the JSP engine should evaluate the tag body
     * again, otherwise return SKIP_BODY. This method is automatically
     * generated. Do not modify this method. Instead, modify the methods that
     * this method calls.
     */
    @Override
    public int doAfterBody() throws JspException {
        if (list.size() > counter) {
            evaluateActiveRecord(list.get(counter));
            counter++;
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

}
