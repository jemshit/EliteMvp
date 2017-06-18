/*
 * Copyright (c) 2017 Jemshit Iskanderov
 * Copyright (c) Jens Dirller, Hannes Dorfmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.jemshit.elitemvp.null_view_presenter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static java.lang.reflect.Proxy.newProxyInstance;
import static java.util.Collections.unmodifiableMap;

/**
 * Utility class for creating <b>No Operation Class</b> with empty methods
 */
final class NullObjectUtility {

    private static final InvocationHandler invocationHandlerDefaults = new DefaultValueInvocationHandler();
    private static final Map<Class<?>, Object> DEFAULTS =
            unmodifiableMap(
                    new HashMap<Class<?>, Object>() {
                        {
                            put(Boolean.TYPE, false);
                            put(Byte.TYPE, (byte) 0);
                            put(Character.TYPE, '\000');
                            put(Double.TYPE, 0.0d);
                            put(Float.TYPE, 0.0f);
                            put(Integer.TYPE, 0);
                            put(Long.TYPE, 0L);
                            put(Short.TYPE, (short) 0);
                        }
                    });


    @SuppressWarnings("unchecked")
    private static <T> T defaultValue(Class<T> type) {
        return (T) DEFAULTS.get(type);
    }

    /**
     * @param interfaceClass Empty Class to be created with NoOp methods
     */
    @SuppressWarnings("unchecked")
    static <T> T getNoOpObject(Class<T> interfaceClass) {
        return (T) newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                invocationHandlerDefaults
        );
    }

    private static class DefaultValueInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return defaultValue(method.getReturnType());
        }
    }
}
