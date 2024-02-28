package com.trantorinc.synergy.common.service.ldap;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.trantorinc.synergy.common.service.dto.LdapDto;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class LdapService {

    private LdapService(){
        // default constructor
    }
    private static final Log log = LogFactoryUtil.getLog(LdapService.class.getName());
    private static final String LDAP_SEARCHBASE = "OU=CHD-Users,DC=trantorchd,DC=com";

    public static List<LdapDto> getLdapSearchResult(String firstname, String lastname, String emailId, String ecode)  {
        List<LdapDto> employees =  new ArrayList<>();
        try{
            Hashtable<String, Object> env = new Hashtable<>();
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "CN=Synergy Account,OU=Domain_Serviceaccount,DC=trantorchd,DC=com");
            env.put(Context.SECURITY_CREDENTIALS, "Syn3rgy@6!$1");
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldaps://192.168.176.20:636");

            SearchControls searchControls = new SearchControls();
            String[] attributeFilter = { "givenName", "sn","displayName", "mail", "pager", "department","title", "description","mobile","physicaldeliveryofficename","whenCreated","manager" };
            searchControls.setReturningAttributes(attributeFilter);
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            LdapContext ctx = new InitialLdapContext(env, null);
            String searchFilter = getFilter(getValue(firstname), getValue(lastname), getValue(emailId), getValue(ecode));

            NamingEnumeration<SearchResult> results = ctx.search(LDAP_SEARCHBASE, searchFilter, searchControls);


            while (results.hasMore()) {
                LdapDto userBean = new LdapDto();

                SearchResult sr = results.next();
                Attributes attrs = sr.getAttributes();

                Attribute attr = attrs.get("givenName");
                userBean.setEmployeeFirstName((attr != null)?attr.get().toString():null);

                attr = attrs.get("sn");
                userBean.setEmployeeLastName((attr != null)?attr.get().toString():null);

                attr = attrs.get("mail");
                userBean.setEmployeeEmail((attr != null)?attr.get().toString():null);

                attr = attrs.get("pager");
                userBean.setEmployeeSkype((attr != null)?attr.get().toString():null);

                attr = attrs.get("department");
                userBean.setEmployeeProject((attr != null)?attr.get().toString():null);

                attr = attrs.get("title");
                userBean.setEmployeeDesignation((attr != null)?attr.get().toString():null);

                attr = attrs.get("description");
                userBean.setEmployeeId((attr != null)?attr.get().toString():null);

                attr = attrs.get("displayName");
                userBean.setEmployeeName((attr != null)?attr.get().toString():null);

                attr = attrs.get("mobile");
                userBean.setEmployeeMobile((attr != null)?attr.get().toString():null);

                attr = attrs.get("physicaldeliveryofficename");
                userBean.setEmployeeLocation((attr != null)?attr.get().toString():null);

                attr = attrs.get("whenCreated");
                if(attr != null){
                    String joiningDateRaw = attr.get().toString();
                    String dateString = joiningDateRaw.substring(0,4)+"-"+joiningDateRaw.substring(4,6)+"-"+joiningDateRaw.substring(6,8);
                    userBean.setEmployeeJoiningDate(LocalDate.parse(dateString));
                }

                attr = attrs.get("manager");
                String managerName = null;
                if(attr != null){
                    String[] managerNameRaw = attr.get().toString().split(",");
                    for(String s : managerNameRaw ){
                        if(s.startsWith("CN=")) {
                            managerName = s.substring(s.indexOf('=')+1);
                            break;
                        }
                    }
                }
                userBean.setEmployeeManagerName(managerName);


                employees.add(userBean);
            }
        } catch(NamingException exception){
            log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
        }
        return employees;
    }

    private static String getValue(String a) {
        if(Validator.isNotNull(a)) {
            return "*"+a+"*";
        }
        return null;
    }

    private static String getFilter(String firstname,String lastname,String emailId,String ecode){
        String filter =null;
        String firstNameFilter ="";
        String lastNameFilter ="";
        String emailFilter ="";
        String ecodeFilter ="";
        if(firstname != null){
            firstNameFilter = "(givenName="+ firstname +")";
        }
        if(lastname != null){
            lastNameFilter = "(sn=" + lastname +")";
        }
        if(emailId != null){
            emailFilter =  "(mail=" + emailId +")";
        }
        if(ecode != null){
            ecodeFilter =  "(description=" + emailId +")";
        }
        filter = firstNameFilter + lastNameFilter + emailFilter + ecodeFilter;
        if(filter.replaceAll("[^=]","").length() > 1) {
            filter = "(&" + filter + ")";
        }
        return filter;
    }

}
