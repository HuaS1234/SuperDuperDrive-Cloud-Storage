package com.udacity.jwdnd.course1.cloudstorage.Constant;

public class MsgConstants {

    public static final String defaultError = "Something went wrong, Please try again";

    public static final String signupError_userExists = "The username already exists.";
    public static final String signupError_other = "There was an error signing you up. Please try again.";

    public static final String fileError_duplicate = "The file has been uploaded already!";
    public static final String fileError_empty = "Choose a file first!";
    public static final String fileError_exceedLimit = "File size limit exceeded!";

    public static final String noteError_exceedLimit = "You have exceeded the size limit(" + SizeConstants.noteMaxSize + ") on your note!";

    public static final String getSuccessMsg_add(String category) {
        return "You have been added a " + category + " successfully!";
    }

    public static final String getSuccessMsg_edit(String category) {
        return "You have been edited a " + category + " successfully!";
    }

    public static final String getSuccessMsg_delete(String category) {
        return "You have been deleted a " + category + " successfully!";
    }
}
