package suai.trading.core.service.bankaccount.command;

import suai.trading.core.service.Id;

public interface BankAccountCommandService {

    Id create(CreateBankAccountCommand command);
}
