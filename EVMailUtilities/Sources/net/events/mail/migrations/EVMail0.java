package net.events.mail.migrations;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class EVMail0 extends ERXMigrationDatabase.Migration {
	@Override
	public NSArray<ERXModelVersion> modelDependencies() {
		return null;
	}
  
	@Override
	public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		// DO NOTHING
	}

	@Override
	public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		ERXMigrationTable eVMailQueueTable = database.newTableNamed("ev_mail_queue");
		eVMailQueueTable.newTimestampColumn("creation_time", false);
		eVMailQueueTable.newIntegerColumn("mail_queue_id", false);
		eVMailQueueTable.newStringColumn("queue_name", 255, false);
		eVMailQueueTable.newTimestampColumn("scheduled_time", false);
		eVMailQueueTable.create();
	 	eVMailQueueTable.setPrimaryKey("mail_queue_id");

		ERXMigrationTable eVMailMessageTable = database.newTableNamed("ev_mail_message");
		eVMailMessageTable.newFlagBooleanColumn("archive_sent_mail", false);
		eVMailMessageTable.newStringColumn("bcc", 10000000, true);
		eVMailMessageTable.newStringColumn("cc", 10000000, true);
		eVMailMessageTable.newTimestampColumn("creation_time", false);
		eVMailMessageTable.newStringColumn("failure_reason", 10000000, true);
		eVMailMessageTable.newStringColumn("from", 255, true);
		eVMailMessageTable.newStringColumn("html_body", 10000000, true);
		eVMailMessageTable.newFlagBooleanColumn("is_read", false);
		eVMailMessageTable.newIntegerColumn("mail_message_id", false);
		eVMailMessageTable.newIntegerColumn("mail_queue_id", true);
		eVMailMessageTable.newStringColumn("plain_text_body", 10000000, true);
		eVMailMessageTable.newStringColumn("reply_to", 255, true);
		eVMailMessageTable.newTimestampColumn("sent_time", true);
		eVMailMessageTable.newStringColumn("status", 50, false);
		eVMailMessageTable.newStringColumn("subject", 1000, false);
		eVMailMessageTable.newStringColumn("to", 10000000, false);
		eVMailMessageTable.newStringColumn("x_mailer", 255, true);
		eVMailMessageTable.create();
	 	eVMailMessageTable.setPrimaryKey("mail_message_id");

		ERXMigrationTable eVMailTemplateTable = database.newTableNamed("ev_mail_template");
		eVMailTemplateTable.newIntegerColumn("mail_template_id", false);
		eVMailTemplateTable.newStringColumn("subject", 1000, false);
		eVMailTemplateTable.newStringColumn("template", 10000000, false);
		eVMailTemplateTable.newStringColumn("template_description", 1000, true);
		eVMailTemplateTable.newStringColumn("template_name", 255, false);
		eVMailTemplateTable.create();
	 	eVMailTemplateTable.setPrimaryKey("mail_template_id");

		eVMailMessageTable.addForeignKey("mail_queue_id", "ev_mail_queue", "mail_queue_id");
	}
}