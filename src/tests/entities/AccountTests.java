package tests.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.Account;
import tests.factory.AccountFactory;

public class AccountTests {

	@Test
	public void depositShouldIncreaseBalanceWhenPositiveAmount() {

		// We will use AAA Pattern - Arrange, Act, Assert

		// Arrange - prepare the data
		double amount = 200.0;
		double expectedValue = 196.0;
		Account account = AccountFactory.createEmptyAccount();

		// Act - Execute the actions needed
		account.deposit(amount);

		// Assert - declare what need to happen
		Assertions.assertEquals(expectedValue, account.getBalance());
	}

	@Test
	public void depositShouldDoNothingWhenNegativeAmount() {
		double expectedValue = 100.0;
		Account account = AccountFactory.createAccount(expectedValue);
		double amount = -200.0;
		account.deposit(amount);
		Assertions.assertEquals(expectedValue, account.getBalance());
	}

	@Test
	public void fullWithdrawShouldClearBalanceAndReturnFullBalance() {
		double expectedValue = 0.0;
		double initialBalance = 800.0;
		Account account = AccountFactory.createAccount(initialBalance);

		double result = account.fullWithdraw();
		Assertions.assertTrue(expectedValue == account.getBalance());
		Assertions.assertTrue(result == initialBalance);
	}

	@Test
	public void withdrawShouldDecreaseBalanceWhenSufficientBalance() {
		double expectedValue = 300.0;
		Account account = AccountFactory.createAccount(800.0);
		account.withdraw(500.0);
		Assertions.assertTrue(expectedValue == account.getBalance());
	}

	@Test
	public void withdrawShouldThrowExceptionWhenInsufficientBalance() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Account account = AccountFactory.createAccount(800.0);
			account.withdraw(801.0);
		});
	}

}
