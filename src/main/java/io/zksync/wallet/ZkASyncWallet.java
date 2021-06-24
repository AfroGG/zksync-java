package io.zksync.wallet;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.web3j.tuples.generated.Tuple2;

import io.zksync.domain.TimeRange;
import io.zksync.domain.fee.TransactionFee;
import io.zksync.domain.state.AccountState;
import io.zksync.domain.swap.Order;
import io.zksync.domain.token.NFT;
import io.zksync.domain.token.Token;
import io.zksync.domain.transaction.ZkSyncTransaction;
import io.zksync.provider.AsyncProvider;

public interface ZkASyncWallet {
    CompletableFuture<String> setSigningKey(TransactionFee fee, Integer nonce, boolean onchainAuth, TimeRange timeRange);

    CompletableFuture<String> syncTransfer(String to, BigInteger amount, TransactionFee fee, Integer nonce, TimeRange timeRange);

    CompletableFuture<String> syncWithdraw(String ethAddress,
                        BigInteger amount,
                        TransactionFee fee,
                        Integer nonce,
                        boolean fastProcessing,
                        TimeRange timeRange);

    CompletableFuture<String> syncForcedExit(String target, TransactionFee fee, Integer nonce, TimeRange timeRange);

    CompletableFuture<String> syncMintNFT(String recipient, String contentHash, TransactionFee fee, Integer nonce);

    CompletableFuture<String> syncWithdrawNFT(String to, NFT token, TransactionFee fee, Integer nonce, TimeRange timeRange);

    CompletableFuture<List<String>> syncTransferNFT(String to, NFT token, TransactionFee fee, Integer nonce, TimeRange timeRange);

    CompletableFuture<String> syncSwap(Order order1, Order order2, BigInteger amount1, BigInteger amount2, TransactionFee fee, Integer nonce);

    CompletableFuture<Order> buildSignedOrder(String recipient, Token sell, Token buy, Tuple2<BigInteger, BigInteger> ratio, BigInteger amount, Integer nonce, TimeRange timeRange);

    CompletableFuture<Order> buildSignedLimitOrder(String recipient, Token sell, Token buy, Tuple2<BigInteger, BigInteger> ratio, Integer nonce, TimeRange timeRange);

    <T extends ZkSyncTransaction> CompletableFuture<String> submitTransaction(SignedTransaction<T> transaction);

    CompletableFuture<Boolean> isSigningKeySet();

    CompletableFuture<AccountState> getState();

    AsyncProvider getProvider();

    CompletableFuture<String> getPubKeyHash();

    CompletableFuture<Integer> getAccountId();

    CompletableFuture<Integer> getNonce();

    String getAddress();
}
