/*
 * Copyright 2015-2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package com.mydata.userdata.utils;

import com.mydata.userdata.dto.*;
import com.mydata.userdata.entity.*;
import java.lang.annotation.*;
import org.junit.jupiter.params.provider.ArgumentsSource;

/**
 * {@code @InvestmentTestArguments} is an {@link ArgumentsSource} which provides access to object of
 * type Investment.
 *
 * <p>Supported types include {@link AccountDto}, {@link MiscellaneousDto}, {@link MutualFundDto},
 * {@link StockDto}, {@link DepositAccount}, {@link Loan}, {@link Miscellaneous}, {@link
 * MutualFund}, {@link SavingAccount}, and {@link Stock}. Note, however, that only one of the
 * supported types may be specified per {@code @InvestmentTestArguments} declaration.
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ArgumentsSource(InvestmentTestArgumentsProvider.class)
public @interface InvestmentTestArguments {

  /** The {@code type} the class type of object to be created */
  Class<?> type() default void.class;

  /**
   * The enum constant selection mode.
   *
   * <p>Defaults to {@link InvestmentTestArguments.Mode#SINGLE}.
   *
   * @see InvestmentTestArguments.Mode#SINGLE
   * @see InvestmentTestArguments.Mode#LIST
   */
  Mode mode() default Mode.SINGLE;

  /** Enumeration which identify whether the return type should be a single object or a list */
  enum Mode {
    /** Generate only a single argument */
    SINGLE,
    /* Generate a List of argument */
    LIST
  }

  /** if the mode the selected is {@link Mode#LIST), then use this field for setting the list size. Defaults to 10 */
  int count() default 10;

  /** Set this value if a companion object need to be created for the generated argument */
  Class<?> companion() default void.class;

  /**
   * Identify which fields to be skipped while generating the companion object
   *
   * <p>Defaults to {@link {}}.
   */
  String[] skipFields() default {};
}
