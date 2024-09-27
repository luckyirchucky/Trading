package suai.trading.core.service.bankaccount.command;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suai.trading.core.service.Id;
import suai.trading.core.service.bankaccount.BankAccount;
import suai.trading.core.service.bankaccount.BankAccountRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BankAccountCommandServiceImpl implements BankAccountCommandService {

    private final BankAccountRepository bankAccountRepository;

    @Override
    public Id create(CreateBankAccountCommand command) {
        var bankAccount = new BankAccount(command.getCardNumber(), command.getCardholderName(), command.getExpirationDate(), command.getCvv());
        bankAccountRepository.save(bankAccount);
        return new Id(bankAccount.getId());
    }
}
