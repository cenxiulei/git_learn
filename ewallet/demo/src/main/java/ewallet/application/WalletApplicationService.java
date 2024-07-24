package ewallet.application;

import ewallet.domain.model.Wallet;
import ewallet.domain.service.WalletService;
import ewallet.dto.WalletDTO;
import ewallet.mapper.WalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletApplicationService {

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletMapper walletMapper;

    public WalletDTO createWallet(WalletDTO walletDTO) {
        Wallet wallet = walletMapper.dtoToEntity(walletDTO);
        return walletMapper.entityToDto(walletService.createWallet(wallet));
    }

    public WalletDTO getWallet(String id) {
        return walletMapper.entityToDto(walletService.getWallet(id));
    }
}
