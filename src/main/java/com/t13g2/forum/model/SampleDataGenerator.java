package com.t13g2.forum.model;

import com.t13g2.forum.model.forum.Announcement;
import com.t13g2.forum.model.forum.Comment;
import com.t13g2.forum.model.forum.ForumThread;
import com.t13g2.forum.model.forum.Module;
import com.t13g2.forum.model.forum.User;
//@@author Meowzz95

/**
 * Helps to generate some sample data
 */
public class SampleDataGenerator {
    private UnitOfWork uow;
    private int userId;
    private int moduleId;
    private int forumThreadId;

    public SampleDataGenerator(UnitOfWork uow) {
        this.uow = uow;
    }

    /**
     * Generates sample data
     */
    public void generate() {
        this.generateUser();
        this.generateModule();
        this.generateThread();
        this.generateComment();
        this.generateAnnouncement();
    }

    /**
     * Generates sample user data
     */
    private void generateUser() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setAdmin(true);
        admin.setEmail("email@gmail.com");
        admin.setPhone("12345678");

        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("user1");
        user1.setEmail("user1@email.com");
        user1.setPhone("88888888");
        this.userId = user1.getId();

        uow.getUserRepository().addUser(admin);
        uow.getUserRepository().addUser(user1);
    }

    /**
     * Generates sample module data
     */
    private void generateModule() {
        Module module = new Module("Software Eng", "CS2113");
        uow.getModuleRepository().addModule(module);
        this.moduleId = module.getId();
    }

    /**
     * Generates sample thread data
     */
    private void generateThread() {
        ForumThread forumThread = new ForumThread();
        forumThread.setModuleId(this.moduleId);
        forumThread.setTitle("Hey How is CS2113");
        forumThread.setCreatedByUserId(this.userId);
        uow.getForumThreadRepository().addThread(forumThread);
        this.forumThreadId = forumThread.getId();
    }

    /**
     * Generates sample comment data
     */
    private void generateComment() {
        Comment comment = new Comment();
        comment.setThreadId(this.forumThreadId);
        comment.setContent("I am taking this module next sem, how is it?");
        comment.setCreatedByUserId(this.userId);

        uow.getCommentRepository().addComment(comment);
    }

    /**
     * Generates sample announcement data
     */
    private void generateAnnouncement() {
        Announcement announcement = new Announcement("Welcome!", "Welcome to ForumBook");
        uow.getAnnouncementRepository().addAnnouncement(announcement);
    }
}
