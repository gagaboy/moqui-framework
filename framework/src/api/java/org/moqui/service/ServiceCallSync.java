/*
 * This software is in the public domain under CC0 1.0 Universal plus a 
 * Grant of Patent License.
 * 
 * To the extent possible under law, the author(s) have dedicated all
 * copyright and related and neighboring rights to this software to the
 * public domain worldwide. This software is distributed without any
 * warranty.
 * 
 * You should have received a copy of the CC0 Public Domain Dedication
 * along with this software (see the LICENSE.md file). If not, see
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package org.moqui.service;

import java.util.Map;

public interface ServiceCallSync extends ServiceCall {
    /** Name of the service to run. The combined service name, like: "${path}.${verb}${noun}". To explicitly separate
     * the verb and noun put a hash (#) between them, like: "${path}.${verb}#${noun}" (this is useful for calling the
     * implicit entity CrUD services where verb is create, update, or delete and noun is the name of the entity).
     */
    ServiceCallSync name(String serviceName);

    ServiceCallSync name(String verb, String noun);

    ServiceCallSync name(String path, String verb, String noun);

    /** Map of name, value pairs that make up the context (in parameters) passed to the service. */
    ServiceCallSync parameters(Map<String, ?> context);

    /** Single name, value pairs to put in the context (in parameters) passed to the service. */
    ServiceCallSync parameter(String name, Object value);

    /** By default a service uses the existing transaction or begins a new one if no tx is in place. Set this flag to
     * ignore the transaction, not checking for one or starting one if no transaction is in place. */
    ServiceCallSync ignoreTransaction(boolean ignoreTransaction);
    /** If true suspend/resume the current transaction (if a transaction is active) and begin a new transaction for the
     * scope of this service call.
     * @return Reference to this for convenience.
     */
    ServiceCallSync requireNewTransaction(boolean requireNewTransaction);
    ServiceCallSync useTransactionCache(boolean useTransactionCache);

    /** Normally service won't run if there was an error (ec.message.hasError()), set this to true to run anyway. */
    ServiceCallSync ignorePreviousError(boolean ipe);

    /** If true expect multiple sets of parameters passed in a single map, each set with a suffix of an underscore
     * and the row of the number, ie something like "userId_8" for the userId parameter in the 8th row.
     * @return Reference to this for convenience.
     */
    ServiceCallSync multi(boolean mlt);

    /** Disable authorization for the current thread during this service call. */
    ServiceCallSync disableAuthz();

    /* * If null defaults to configured value for service, or container. For possible values see JavaDoc for javax.sql.Connection.
     * @return Reference to this for convenience.
     */
    /* not supported by Atomikos/etc right now, consider for later: ServiceCallSync transactionIsolation(int transactionIsolation);

    /** Call the service synchronously and immediately get the result.
     * @return Map containing the result (out parameters) from the service call.
     */
    Map<String, Object> call() throws ServiceException;
}
