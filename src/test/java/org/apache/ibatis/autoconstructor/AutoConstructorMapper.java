/**
 *    Copyright 2009-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.autoconstructor;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface AutoConstructorMapper {
  @Select("SELECT * FROM subject WHERE id = #{id}")
  PrimitiveSubject getSubject(final int id);

  /*
   *  PrimitiveSubject对应Subject表  PrimitiveSubject与AnnotatedSubject不同，在其构造方法上，weight和height方法参数的类型是int，而不是Integer。
   *  如果subject表中的记录，这两个字段为NULL时，会创建PrimitiveSubject对象报错
   */
  @Select("SELECT * FROM subject")
  List<PrimitiveSubject> getSubjects();

  /*
   *  AnnotatedSubjectd对应Subject表
   */
  @Select("SELECT * FROM subject")
  List<AnnotatedSubject> getAnnotatedSubjects();

  /**
   *  BadSubject对应的也是subject表
   *  和AnnotatedSubject不同，在其构造方法上，height方法参数的类型是height，而不是Integer
   *  因为MyBatis无法识别Height类，所以会创建BadSubject对象报错
   *  一般情况下，POJO对象里，不使用基本类型
   */
  @Select("SELECT * FROM subject")
  List<BadSubject> getBadSubjects();

  /*
   *  ExtensiveSubject对应extensive_subject表
   *  这是个复杂对象，基本涵盖了各种类型的数据
   */
  @Select("SELECT * FROM extensive_subject")
  List<ExtensiveSubject> getExtensiveSubjects();
}
