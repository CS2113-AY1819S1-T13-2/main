package com.t13g2.forum.logic.commands;

import static java.util.Objects.requireNonNull;

import com.t13g2.forum.logic.CommandHistory;
import com.t13g2.forum.model.Model;
import com.t13g2.forum.model.forum.Announcement;
import com.t13g2.forum.storage.forum.UnitOfWork;

/**
 * Allow user to check if there is any announcement
 */
public class CheckAnnouncmentCommand extends Command {
    public static final String COMMAND_WORD = "checkAnnounce";

    public static final String MESSAGE_SUCCESS = "Showing new announcement: %1$s";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        Announcement announcement = new Announcement();
        try(UnitOfWork unitOfWork = new UnitOfWork()) {
            int newAnnouncementId = unitOfWork.getAnnouncementRepository().getLatestAnnouncement().getId();
            announcement = unitOfWork.getAnnouncementRepository().getAnnouncement(newAnnouncementId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, announcement));
    }
}