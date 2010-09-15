/*
 * IPersistenceModule.java
 * ----------------------------------
 * Este arquivo pertence à Petrobras e não pode ser utilizado fora desta empresa sem prévia autorização.
 * ----------------------------------
 * Esta classe segue o padrao PE-1T0-00315
 */
package com.kameshi.framework.module.transaction;

import com.kameshi.framework.kernel.module.IKernelModule;


/**
 * Interface do modulo de transacao do kernel
 */
public interface ITransactionModule extends IKernelModule{
    /**
     * Realiza o commit da transacao
     */
    public void commit();

    /**
     * Inicia uma transacao
     */
    public void beginTransaction();

    /**
     * Realiza o rollback da transacao
     */
    public void rollback();
}