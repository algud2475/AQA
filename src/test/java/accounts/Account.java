package accounts;

import constants.Constants;
import utils.RandomStringUtil;

public class Account {
    private String email;
    private String password;
    private String domain;

    public static Account getValidAccount() {
        Account account = new Account();
        account.setEmail(RandomStringUtil.getRandomString(Constants.PASSWORD_MIN_LENGTH));
        account.setPassword(RandomStringUtil.getRandomPassword(account.getEmail(), Constants.PASSWORD_MIN_LENGTH));
        account.setDomain(RandomStringUtil.getRandomString(Constants.PASSWORD_MIN_LENGTH));
        return account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
