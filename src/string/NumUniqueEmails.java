package string;

import java.util.HashSet;

public class NumUniqueEmails {
    public static int numUniqueEmails(String[] emails) {
        if (emails == null || emails.length == 0) {
            return 0;
        }

        HashSet<String> uniqueEmails = new HashSet<String>();

        for (String email : emails) {
            if (email.indexOf('@') > 0)
            {
                String newEmail = email.substring(0, email.indexOf('@')).replace(".", "") + email.substring(email.indexOf('@'));

                if (newEmail.indexOf('+') > 0) {
                    newEmail = newEmail.substring(0, newEmail.indexOf('+')) + newEmail.substring(newEmail.indexOf('@')) ;
                }

                if (!uniqueEmails.contains(newEmail))
                {
                    uniqueEmails.add(newEmail);
                }
            }
        }

        return uniqueEmails.toArray().length;
    }

    public static void main(String[] args) {
        System.out.println(numUniqueEmails(new String[] {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"}));
    }
}
