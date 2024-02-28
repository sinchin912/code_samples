package com.trantorinc.synergy.custom.service;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.*;
import com.trantorinc.synergy.custom.service.constants.SynergyServicePortletKeys;
import com.trantorinc.synergy.email.core.model.Email;
import com.trantorinc.synergy.email.core.service.EmailLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.trantorinc.synergy.common.service.constant.AppConstantService.*;
import static com.trantorinc.synergy.common.service.util.UtilService.getIstDate;


@Component(
		immediate = true,
		service = ServiceWrapper.class
)
public class UserServiceWrapper extends UserLocalServiceWrapper {
	private static final Log log = LogFactoryUtil.getLog(UserServiceWrapper.class);

	public UserServiceWrapper() {

		super(null);
	}
	private List<Role> oldRoles= new ArrayList<>();
	@Override
	public void addRoleUsers(long roleId, long[] userIds) throws PortalException {
		StringBuilder allUsers =new StringBuilder();
		for(long arr : userIds)
		{
			allUsers.append(UserLocalServiceUtil.getUser(arr).getFullName());
		}
		Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
		email.setToAddress(DL_INTRANET);
		email.setSubject(MessageFormat.format(SynergyServicePortletKeys.SUBJECT_ROLE_ASSIGNED,ENVIRONMENT));
		email.setBody(MessageFormat.format(SynergyServicePortletKeys.BODY_ROLE_ASSIGNED, allUsers,RoleLocalServiceUtil.getRole(roleId).getName()));
		email.setModule(MODULE_GENERIC);
		email.setScheduled(false);
		email.setSent(false);
		email.setCreatedDate(getIstDate());
		EmailLocalServiceUtil.addEmail(email);
		super.addRoleUsers(roleId, userIds);
	}

	@Override
	public void unsetRoleUsers(long roleId, long[] userIds) throws PortalException {
		StringBuilder allUsers =new StringBuilder();
		for(long arr : userIds)
		{
			allUsers.append(UserLocalServiceUtil.getUser(arr).getFullName());
		}
		Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
		email.setToAddress(DL_INTRANET);
		email.setSubject(MessageFormat.format(SynergyServicePortletKeys.SUBJECT_ROLE_REMOVED,ENVIRONMENT));
		email.setBody(MessageFormat.format(SynergyServicePortletKeys.BODY_ROLE_REMOVED, allUsers,RoleLocalServiceUtil.getRole(roleId).getName()));
		email.setModule(MODULE_GENERIC);
		email.setScheduled(false);
		email.setSent(false);
		email.setCreatedDate(getIstDate());
		EmailLocalServiceUtil.addEmail(email);
		super.unsetRoleUsers(roleId, userIds);
	}

	@Override
	public User updateUser(
			long userId, String oldPassword, String newPassword1,
			String newPassword2, boolean passwordReset,
			String reminderQueryQuestion, String reminderQueryAnswer,
			String screenName, String emailAddress, boolean hasPortrait,
			byte[] portraitBytes, String languageId, String timeZoneId,
			String greeting, String comments, String firstName,
			String middleName, String lastName, long prefixListTypeId,
			long suffixListTypeId, boolean male, int birthdayMonth,
			int birthdayDay, int birthdayYear, String smsSn, String facebookSn,
			String jabberSn, String skypeSn, String twitterSn, String jobTitle,
			long[] groupIds, long[] organizationIds, long[] roleIds,
			List<com.liferay.portal.kernel.model.UserGroupRole>
					userGroupRoles,
			long[] userGroupIds, ServiceContext serviceContext)
			throws PortalException {
		oldRoles = RoleLocalServiceUtil.getUserRoles(userId);

		if(roleIds!=null)
		{
			if(matchRoleIds(roleIds,userId))
			{
				log.info("Roles not updated");
			}
			else {
				log.info("Roles Updated");
			}
		}

		return super.updateUser(
				userId, oldPassword, newPassword1, newPassword2, passwordReset,
				reminderQueryQuestion, reminderQueryAnswer, screenName,
				emailAddress, hasPortrait, portraitBytes, languageId, timeZoneId,
				greeting, comments, firstName, middleName, lastName,
				prefixListTypeId, suffixListTypeId, male, birthdayMonth,
				birthdayDay, birthdayYear, smsSn, facebookSn, jabberSn, skypeSn,
				twitterSn, jobTitle, groupIds, organizationIds, roleIds,
				userGroupRoles, userGroupIds, serviceContext);
	}
	private boolean matchRoleIds(long[] roleIds,long userId) {
		try {
			List<Role> currentRolesForUser =  RoleLocalServiceUtil.getUserRoles(userId);
			List<Long> gettingRoleForUser = new ArrayList<>();
			for(Role  r1 : currentRolesForUser) {

				gettingRoleForUser.add(r1.getRoleId());
			}
			List<Long> gettingRoleIds = new ArrayList<>();
			for(long roleId: roleIds) {

				gettingRoleIds.add(roleId);
			}
			Collections.sort(gettingRoleIds);
			Collections.sort(gettingRoleForUser);
			if(gettingRoleForUser.size()>gettingRoleIds.size())
			{

				for(long role:gettingRoleForUser)
				{
					boolean isPresent = false;
					for(long l: roleIds) {
						if(l==role) {
							isPresent = true;
							break;
						}
					}
					if(!isPresent) {
						Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
						email.setToAddress(DL_INTRANET);
						email.setSubject(MessageFormat.format(SynergyServicePortletKeys.SUBJECT_USER_ROLE_DELETE,ENVIRONMENT));
						email.setBody(MessageFormat.format(SynergyServicePortletKeys.BODY_USER_ROLE_DELETE, RoleLocalServiceUtil.getRole(role).getName(),UserLocalServiceUtil.getUser(userId).getFullName()));
						email.setModule(MODULE_GENERIC);
						email.setScheduled(false);
						email.setSent(false);
						email.setCreatedDate(getIstDate());
						EmailLocalServiceUtil.addEmail(email);
					}
				}
			}
			else
			{
				StringBuilder allRoles =new StringBuilder();
				for(long arr : roleIds)
				{
					boolean newRole=true;
					for (Role role:oldRoles) {
						if(role.getRoleId() == arr) {
							newRole=false;
							break;
						}
					}
					if(newRole) {
						allRoles.append(RoleLocalServiceUtil.getRole(arr).getName());
					}
				}
				Email email = EmailLocalServiceUtil.createEmail(CounterLocalServiceUtil.increment());
				email.setToAddress(DL_INTRANET);
				email.setSubject(MessageFormat.format(SynergyServicePortletKeys.SUBJECT_USER_ROLE_ADD,ENVIRONMENT));
				email.setBody(MessageFormat.format(SynergyServicePortletKeys.BODY_USER_ROLE_ADD, allRoles, UserLocalServiceUtil.getUser(userId).getFullName()));
				email.setModule(MODULE_GENERIC);
				email.setScheduled(false);
				email.setSent(false);
				email.setCreatedDate(getIstDate());
				EmailLocalServiceUtil.addEmail(email);
			}
		} catch(PortalException exception) {
			log.error(exception.getStackTrace()[0].getMethodName()+":" +exception.getStackTrace()[0].getLineNumber()+":"+ exception.getMessage());
		}
		return false;
	}
}