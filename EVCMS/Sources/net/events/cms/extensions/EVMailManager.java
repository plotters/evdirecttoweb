package net.events.cms.extensions;

import com.webobjects.appserver.*;

import er.javamail.*;

import net.events.cms.eo.*;

public class EVMailManager {

	/**
	 * Sends a notification e-mail to the author of the blog entry, that a new
	 * comment was created. Includes the comment in the email.
	 * 
	 * @param comment -
	 *            the comment
	 * @param request -
	 *            the request from the page
	 */
	public static void sendNotificationMailForBlogCommentInRequest(BlogComment comment, WORequest request) {
		StringBuffer sb = new StringBuffer();

		// FIXME cug: this uses the language from the request of the current reader, not the preferred language of the user which it should
		sb.append(EVLocalizationHelper.localizedStringForKeyAndRequest("CommentMail.NAME", request) + ": " + comment.name() + "\n");
		sb.append(EVLocalizationHelper.localizedStringForKeyAndRequest("CommentMail.EMAIL", request) + ": " + comment.email() + "\n\n");
		sb.append(EVLocalizationHelper.localizedStringForKeyAndRequest("CommentMail.COMMENT", request) + ": " + "\n\n");
		sb.append(comment.comment());

		ERMailDeliveryPlainText mail = new ERMailDeliveryPlainText();
		try {
			mail.newMail();
			mail.setFromAddress("designer@event-s.net");
			mail.setReplyToAddress(comment.email());
			mail.setToAddress(comment.blogEntry().createdBy().email());
			mail.setSubject(EVLocalizationHelper.localizedStringForKeyAndRequest("CommentMail.SUBJECT_PREFIX", request) + ": " + comment.blogEntry().title());
			mail.setTextContent(sb.toString());

			mail.sendMail();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
