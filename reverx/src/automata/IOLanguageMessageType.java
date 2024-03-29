/*****************************************************************************
 * [Simplified BSD License]
 *
 * Copyright 2011 João Antunes. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY JOÃO ANTUNES ''AS IS'' AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL JOÃO ANTUNES OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of João Antunes.
 *****************************************************************************/

package automata;

import java.util.*;

public class IOLanguageMessageType implements MessageType, java.io.Serializable {
  private static final long serialVersionUID = LanguageMessageType.serialVersionUID;
  protected List<LanguageMessageType> _input, _output;

  public IOLanguageMessageType(List<LanguageMessageType> input, List<LanguageMessageType> output) {
    _input = input;
    _output = output;
  }

  public List<LanguageMessageType> getInput() {
    return _input;
  }

  public List<LanguageMessageType> getOutput() {
    return _output;
  }

  @Override
  public int hashCode() {
    return _input.hashCode() ^ _output.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof IOLanguageMessageType) {
      IOLanguageMessageType other = (IOLanguageMessageType)obj;
      return (_input.equals(other._input) && _output.equals(other._output));
    }
    return super.equals(obj);
  }

  @Override
  public String toString() {
    String in = utils.Print.green(_input.toString().replaceAll("\\[|\\]", ""));
    String out = utils.Print.yellow(_output.toString().replaceAll("\\[|\\]", ""));
    return "[" + in + "/" + out + "]";
  }

  public String toDot() {
    StringBuffer sb = new StringBuffer();
    // input
    Iterator<LanguageMessageType> iter = _input.iterator();
    while (iter.hasNext()) {
      sb.append(iter.next().toDot());
      if (iter.hasNext())
        sb.append(", ");
    }
    // output
    sb.append("/");
    iter = _output.iterator();
    while (iter.hasNext()) {
      sb.append(iter.next().toDot());
      if (iter.hasNext())
        sb.append(", ");
    }
    return sb.toString();
  }

  @Override
  public Object clone() {
    List<LanguageMessageType> input_clone = new ArrayList<LanguageMessageType>(_input);
    List<LanguageMessageType> output_clone = new ArrayList<LanguageMessageType>(_output);
    return new IOLanguageMessageType(input_clone, output_clone);
  }

}
