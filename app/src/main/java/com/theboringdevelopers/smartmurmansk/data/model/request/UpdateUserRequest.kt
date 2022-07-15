/*
 * STRICTLY CONFIDENTIAL
 * TRADE SECRET
 * PROPRIETARY:
 *       "BIFIT" JSC, TIN 7719617469
 *       105203, Russia, Moscow, Nizhnyaya Pervomayskaya, 46
 * (c) "BIFIT" JSC, 2022
 *
 * СТРОГО КОНФИДЕНЦИАЛЬНО
 * КОММЕРЧЕСКАЯ ТАЙНА
 * СОБСТВЕННИК:
 *       АО "БИФИТ", ИНН 7719617469
 *       105203, Россия, Москва, ул. Нижняя Первомайская, д. 46
 * (c) АО "БИФИТ", 2022
 */

package com.theboringdevelopers.smartmurmansk.data.model.request

import com.theboringdevelopers.smartmurmansk.data.model.bean.SportType
import com.theboringdevelopers.smartmurmansk.data.model.bean.UserInfo

data class UpdateUserRequest(
    val user: UserInfo,
    val sportTypeList: List<SportType>
)