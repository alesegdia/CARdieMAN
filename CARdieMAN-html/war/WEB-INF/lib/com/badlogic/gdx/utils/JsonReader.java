// line 1 "JsonReader.rl"
// Do not edit this file! Generated by Ragel.
// Ragel.exe -G2 -J -o JsonReader.java JsonReader.rl
/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonValue.ValueType;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/** Lightweight JSON parser.<br>
 * <br>
 * The default behavior is to parse the JSON into a DOM containing {@link JsonValue} objects. Extend this class and override
 * methods to perform event driven parsing. When this is done, the parse methods will return null.
 * @author Nathan Sweet */
public class JsonReader implements BaseJsonReader {
	public JsonValue parse (String json) {
		char[] data = json.toCharArray();
		return parse(data, 0, data.length);
	}

	public JsonValue parse (Reader reader) {
		try {
			char[] data = new char[1024];
			int offset = 0;
			while (true) {
				int length = reader.read(data, offset, data.length - offset);
				if (length == -1) break;
				if (length == 0) {
					char[] newData = new char[data.length * 2];
					System.arraycopy(data, 0, newData, 0, data.length);
					data = newData;
				} else
					offset += length;
			}
			return parse(data, 0, offset);
		} catch (IOException ex) {
			throw new SerializationException(ex);
		} finally {
			StreamUtils.closeQuietly(reader);
		}
	}

	@Override
	public JsonValue parse (InputStream input) {
		try {
			return parse(new InputStreamReader(input, "ISO-8859-1"));
		} catch (IOException ex) {
			throw new SerializationException(ex);
		} finally {
			StreamUtils.closeQuietly(input);
		}
	}

	@Override
	public JsonValue parse (FileHandle file) {
		try {
			return parse(file.read());
		} catch (Exception ex) {
			throw new SerializationException("Error parsing file: " + file, ex);
		}
	}

	public JsonValue parse (char[] data, int offset, int length) {
		int cs, p = offset, pe = length, eof = pe, top = 0;
		int[] stack = new int[4];

		int s = 0;
		Array<String> names = new Array(8);
		boolean needsUnescape = false;
		boolean discardBuffer = false; // When unquotedString and true/false/null both match, this discards unquotedString.
		RuntimeException parseRuntimeEx = null;

		boolean debug = false;
		if (debug) System.out.println();

		try {

			// line 3 "JsonReader.java"
			{
				cs = json_start;
				top = 0;
			}

			// line 8 "JsonReader.java"
			{
				int _klen;
				int _trans = 0;
				int _acts;
				int _nacts;
				int _keys;
				int _goto_targ = 0;

				_goto:
				while (true) {
					switch (_goto_targ) {
					case 0:
						if (p == pe) {
							_goto_targ = 4;
							continue _goto;
						}
						if (cs == 0) {
							_goto_targ = 5;
							continue _goto;
						}
					case 1:
						_match:
						do {
							_keys = _json_key_offsets[cs];
							_trans = _json_index_offsets[cs];
							_klen = _json_single_lengths[cs];
							if (_klen > 0) {
								int _lower = _keys;
								int _mid;
								int _upper = _keys + _klen - 1;
								while (true) {
									if (_upper < _lower) break;

									_mid = _lower + ((_upper - _lower) >> 1);
									if (data[p] < _json_trans_keys[_mid])
										_upper = _mid - 1;
									else if (data[p] > _json_trans_keys[_mid])
										_lower = _mid + 1;
									else {
										_trans += (_mid - _keys);
										break _match;
									}
								}
								_keys += _klen;
								_trans += _klen;
							}

							_klen = _json_range_lengths[cs];
							if (_klen > 0) {
								int _lower = _keys;
								int _mid;
								int _upper = _keys + (_klen << 1) - 2;
								while (true) {
									if (_upper < _lower) break;

									_mid = _lower + (((_upper - _lower) >> 1) & ~1);
									if (data[p] < _json_trans_keys[_mid])
										_upper = _mid - 2;
									else if (data[p] > _json_trans_keys[_mid + 1])
										_lower = _mid + 2;
									else {
										_trans += ((_mid - _keys) >> 1);
										break _match;
									}
								}
								_trans += _klen;
							}
						} while (false);

						cs = _json_trans_targs[_trans];

						if (_json_trans_actions[_trans] != 0) {
							_acts = _json_trans_actions[_trans];
							_nacts = (int)_json_actions[_acts++];
							while (_nacts-- > 0) {
								switch (_json_actions[_acts++]) {
								case 0:
								// line 106 "JsonReader.rl"
								{
									s = p;
									needsUnescape = false;
									discardBuffer = false;
								}
									break;
								case 1:
								// line 111 "JsonReader.rl"
								{
									needsUnescape = true;
								}
									break;
								case 2:
								// line 114 "JsonReader.rl"
								{
									String name = new String(data, s, p - s);
									s = p;
									if (needsUnescape) name = unescape(name);
									if (debug) System.out.println("name: " + name);
									names.add(name);
								}
									break;
								case 3:
								// line 121 "JsonReader.rl"
								{
									if (!discardBuffer) {
										String value = new String(data, s, p - s);
										s = p;
										if (needsUnescape) value = unescape(value);
										String name = names.size > 0 ? names.pop() : null;
										if (debug) System.out.println("string: " + name + "=" + value);
										string(name, value);
									}
								}
									break;
								case 4:
								// line 131 "JsonReader.rl"
								{
									String value = new String(data, s, p - s);
									s = p;
									String name = names.size > 0 ? names.pop() : null;
									if (debug) System.out.println("double: " + name + "=" + Double.parseDouble(value));
									number(name, Double.parseDouble(value));
								}
									break;
								case 5:
								// line 138 "JsonReader.rl"
								{
									String value = new String(data, s, p - s);
									s = p;
									String name = names.size > 0 ? names.pop() : null;
									if (debug) System.out.println("long: " + name + "=" + Long.parseLong(value));
									number(name, Long.parseLong(value));
								}
									break;
								case 6:
								// line 145 "JsonReader.rl"
								{
									String name = names.size > 0 ? names.pop() : null;
									if (debug) System.out.println("boolean: " + name + "=true");
									bool(name, true);
									discardBuffer = true;
								}
									break;
								case 7:
								// line 151 "JsonReader.rl"
								{
									String name = names.size > 0 ? names.pop() : null;
									if (debug) System.out.println("boolean: " + name + "=false");
									bool(name, false);
									discardBuffer = true;
								}
									break;
								case 8:
								// line 157 "JsonReader.rl"
								{
									String name = names.size > 0 ? names.pop() : null;
									if (debug) System.out.println("null: " + name);
									string(name, null);
									discardBuffer = true;
								}
									break;
								case 9:
								// line 163 "JsonReader.rl"
								{
									String name = names.size > 0 ? names.pop() : null;
									if (debug) System.out.println("startObject: " + name);
									startObject(name);
									{
										if (top == stack.length) {
											int[] newStack = new int[stack.length * 2];
											System.arraycopy(stack, 0, newStack, 0, stack.length);
											stack = newStack;
										}
										{
											stack[top++] = cs;
											cs = 8;
											_goto_targ = 2;
											if (true) continue _goto;
										}
									}
								}
									break;
								case 10:
								// line 169 "JsonReader.rl"
								{
									if (debug) System.out.println("endObject");
									pop();
									{
										cs = stack[--top];
										_goto_targ = 2;
										if (true) continue _goto;
									}
								}
									break;
								case 11:
								// line 174 "JsonReader.rl"
								{
									String name = names.size > 0 ? names.pop() : null;
									if (debug) System.out.println("startArray: " + name);
									startArray(name);
									{
										if (top == stack.length) {
											int[] newStack = new int[stack.length * 2];
											System.arraycopy(stack, 0, newStack, 0, stack.length);
											stack = newStack;
										}
										{
											stack[top++] = cs;
											cs = 46;
											_goto_targ = 2;
											if (true) continue _goto;
										}
									}
								}
									break;
								case 12:
								// line 180 "JsonReader.rl"
								{
									if (debug) System.out.println("endArray");
									pop();
									{
										cs = stack[--top];
										_goto_targ = 2;
										if (true) continue _goto;
									}
								}
									break;
								// line 217 "JsonReader.java"
								}
							}
						}

					case 2:
						if (cs == 0) {
							_goto_targ = 5;
							continue _goto;
						}
						if (++p != pe) {
							_goto_targ = 1;
							continue _goto;
						}
					case 4:
						if (p == eof) {
							int __acts = _json_eof_actions[cs];
							int __nacts = (int)_json_actions[__acts++];
							while (__nacts-- > 0) {
								switch (_json_actions[__acts++]) {
								case 3:
								// line 121 "JsonReader.rl"
								{
									if (!discardBuffer) {
										String value = new String(data, s, p - s);
										s = p;
										if (needsUnescape) value = unescape(value);
										String name = names.size > 0 ? names.pop() : null;
										if (debug) System.out.println("string: " + name + "=" + value);
										string(name, value);
									}
								}
									break;
								case 4:
								// line 131 "JsonReader.rl"
								{
									String value = new String(data, s, p - s);
									s = p;
									String name = names.size > 0 ? names.pop() : null;
									if (debug) System.out.println("double: " + name + "=" + Double.parseDouble(value));
									number(name, Double.parseDouble(value));
								}
									break;
								case 5:
								// line 138 "JsonReader.rl"
								{
									String value = new String(data, s, p - s);
									s = p;
									String name = names.size > 0 ? names.pop() : null;
									if (debug) System.out.println("long: " + name + "=" + Long.parseLong(value));
									number(name, Long.parseLong(value));
								}
									break;
								case 6:
								// line 145 "JsonReader.rl"
								{
									String name = names.size > 0 ? names.pop() : null;
									if (debug) System.out.println("boolean: " + name + "=true");
									bool(name, true);
									discardBuffer = true;
								}
									break;
								case 7:
								// line 151 "JsonReader.rl"
								{
									String name = names.size > 0 ? names.pop() : null;
									if (debug) System.out.println("boolean: " + name + "=false");
									bool(name, false);
									discardBuffer = true;
								}
									break;
								case 8:
								// line 157 "JsonReader.rl"
								{
									String name = names.size > 0 ? names.pop() : null;
									if (debug) System.out.println("null: " + name);
									string(name, null);
									discardBuffer = true;
								}
									break;
								// line 298 "JsonReader.java"
								}
							}
						}

					case 5:
					}
					break;
				}
			}

			// line 212 "JsonReader.rl"

		} catch (RuntimeException ex) {
			parseRuntimeEx = ex;
		}

		JsonValue root = this.root;
		this.root = null;
		current = null;
		lastChild.clear();

		if (p < pe) {
			int lineNumber = 1;
			for (int i = 0; i < p; i++)
				if (data[i] == '\n') lineNumber++;
			throw new SerializationException("Error parsing JSON on line " + lineNumber + " near: " + new String(data, p, pe - p),
				parseRuntimeEx);
		} else if (elements.size != 0) {
			JsonValue element = elements.peek();
			elements.clear();
			if (element != null && element.isObject())
				throw new SerializationException("Error parsing JSON, unmatched brace.");
			else
				throw new SerializationException("Error parsing JSON, unmatched bracket.");
		} else if (parseRuntimeEx != null) {
			throw new SerializationException("Error parsing JSON: " + new String(data), parseRuntimeEx);
		}
		return root;
	}

	// line 308 "JsonReader.java"
	private static byte[] init__json_actions_0 () {
		return new byte[] {0, 1, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 9, 1, 10, 1, 11, 1, 12, 2, 0, 2, 2, 0, 3, 2, 3, 10, 2, 3, 12,
			2, 4, 10, 2, 4, 12, 2, 5, 10, 2, 5, 12, 2, 6, 3, 2, 7, 3, 2, 8, 3, 3, 6, 3, 10, 3, 6, 3, 12, 3, 7, 3, 10, 3, 7, 3, 12,
			3, 8, 3, 10, 3, 8, 3, 12};
	}

	private static final byte _json_actions[] = init__json_actions_0();

	private static short[] init__json_key_offsets_0 () {
		return new short[] {0, 0, 18, 20, 22, 31, 33, 37, 39, 54, 56, 58, 62, 80, 82, 84, 89, 103, 110, 112, 115, 123, 127, 129,
			135, 144, 151, 153, 161, 170, 174, 176, 183, 191, 199, 207, 215, 222, 230, 238, 246, 253, 261, 269, 277, 284, 293, 313,
			315, 317, 322, 341, 348, 350, 358, 367, 371, 373, 380, 388, 396, 404, 412, 419, 427, 435, 443, 450, 458, 466, 474, 481,
			490, 493, 500, 506, 513, 518, 526, 534, 542, 550, 557, 565, 573, 581, 588, 596, 604, 612, 619, 619};
	}

	private static final short _json_key_offsets[] = init__json_key_offsets_0();

	private static char[] init__json_trans_keys_0 () {
		return new char[] {32, 34, 36, 45, 91, 95, 102, 110, 116, 123, 9, 13, 48, 57, 65, 90, 97, 122, 34, 92, 34, 92, 34, 47, 92,
			98, 102, 110, 114, 116, 117, 48, 57, 43, 45, 48, 57, 48, 57, 32, 34, 36, 44, 45, 95, 125, 9, 13, 48, 57, 65, 90, 97,
			122, 34, 92, 34, 92, 32, 58, 9, 13, 32, 34, 36, 45, 91, 95, 102, 110, 116, 123, 9, 13, 48, 57, 65, 90, 97, 122, 34, 92,
			34, 92, 32, 44, 125, 9, 13, 32, 34, 36, 45, 95, 125, 9, 13, 48, 57, 65, 90, 97, 122, 32, 44, 58, 93, 125, 9, 13, 48, 57,
			46, 48, 57, 32, 58, 69, 101, 9, 13, 48, 57, 43, 45, 48, 57, 48, 57, 32, 58, 9, 13, 48, 57, 34, 47, 92, 98, 102, 110,
			114, 116, 117, 32, 44, 58, 93, 125, 9, 13, 48, 57, 32, 44, 46, 125, 9, 13, 48, 57, 32, 44, 69, 101, 125, 9, 13, 48, 57,
			43, 45, 48, 57, 48, 57, 32, 44, 125, 9, 13, 48, 57, 32, 44, 58, 93, 97, 125, 9, 13, 32, 44, 58, 93, 108, 125, 9, 13, 32,
			44, 58, 93, 115, 125, 9, 13, 32, 44, 58, 93, 101, 125, 9, 13, 32, 44, 58, 93, 125, 9, 13, 32, 44, 58, 93, 117, 125, 9,
			13, 32, 44, 58, 93, 108, 125, 9, 13, 32, 44, 58, 93, 108, 125, 9, 13, 32, 44, 58, 93, 125, 9, 13, 32, 44, 58, 93, 114,
			125, 9, 13, 32, 44, 58, 93, 117, 125, 9, 13, 32, 44, 58, 93, 101, 125, 9, 13, 32, 44, 58, 93, 125, 9, 13, 34, 47, 92,
			98, 102, 110, 114, 116, 117, 32, 34, 36, 44, 45, 91, 93, 95, 102, 110, 116, 123, 9, 13, 48, 57, 65, 90, 97, 122, 34, 92,
			34, 92, 32, 44, 93, 9, 13, 32, 34, 36, 45, 91, 93, 95, 102, 110, 116, 123, 9, 13, 48, 57, 65, 90, 97, 122, 32, 44, 58,
			93, 125, 9, 13, 48, 57, 32, 44, 46, 93, 9, 13, 48, 57, 32, 44, 69, 93, 101, 9, 13, 48, 57, 43, 45, 48, 57, 48, 57, 32,
			44, 93, 9, 13, 48, 57, 32, 44, 58, 93, 97, 125, 9, 13, 32, 44, 58, 93, 108, 125, 9, 13, 32, 44, 58, 93, 115, 125, 9, 13,
			32, 44, 58, 93, 101, 125, 9, 13, 32, 44, 58, 93, 125, 9, 13, 32, 44, 58, 93, 117, 125, 9, 13, 32, 44, 58, 93, 108, 125,
			9, 13, 32, 44, 58, 93, 108, 125, 9, 13, 32, 44, 58, 93, 125, 9, 13, 32, 44, 58, 93, 114, 125, 9, 13, 32, 44, 58, 93,
			117, 125, 9, 13, 32, 44, 58, 93, 101, 125, 9, 13, 32, 44, 58, 93, 125, 9, 13, 34, 47, 92, 98, 102, 110, 114, 116, 117,
			32, 9, 13, 32, 44, 58, 93, 125, 9, 13, 32, 46, 9, 13, 48, 57, 32, 69, 101, 9, 13, 48, 57, 32, 9, 13, 48, 57, 32, 44, 58,
			93, 97, 125, 9, 13, 32, 44, 58, 93, 108, 125, 9, 13, 32, 44, 58, 93, 115, 125, 9, 13, 32, 44, 58, 93, 101, 125, 9, 13,
			32, 44, 58, 93, 125, 9, 13, 32, 44, 58, 93, 117, 125, 9, 13, 32, 44, 58, 93, 108, 125, 9, 13, 32, 44, 58, 93, 108, 125,
			9, 13, 32, 44, 58, 93, 125, 9, 13, 32, 44, 58, 93, 114, 125, 9, 13, 32, 44, 58, 93, 117, 125, 9, 13, 32, 44, 58, 93,
			101, 125, 9, 13, 32, 44, 58, 93, 125, 9, 13, 0};
	}

	private static final char _json_trans_keys[] = init__json_trans_keys_0();

	private static byte[] init__json_single_lengths_0 () {
		return new byte[] {0, 10, 2, 2, 7, 0, 2, 0, 7, 2, 2, 2, 10, 2, 2, 3, 6, 5, 0, 1, 4, 2, 0, 2, 7, 5, 0, 4, 5, 2, 0, 3, 6, 6,
			6, 6, 5, 6, 6, 6, 5, 6, 6, 6, 5, 7, 12, 2, 2, 3, 11, 5, 0, 4, 5, 2, 0, 3, 6, 6, 6, 6, 5, 6, 6, 6, 5, 6, 6, 6, 5, 7, 1,
			5, 2, 3, 1, 6, 6, 6, 6, 5, 6, 6, 6, 5, 6, 6, 6, 5, 0, 0};
	}

	private static final byte _json_single_lengths[] = init__json_single_lengths_0();

	private static byte[] init__json_range_lengths_0 () {
		return new byte[] {0, 4, 0, 0, 1, 1, 1, 1, 4, 0, 0, 1, 4, 0, 0, 1, 4, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 0, 0, 1, 4, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2,
			2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0};
	}

	private static final byte _json_range_lengths[] = init__json_range_lengths_0();

	private static short[] init__json_index_offsets_0 () {
		return new short[] {0, 0, 15, 18, 21, 30, 32, 36, 38, 50, 53, 56, 60, 75, 78, 81, 86, 97, 104, 106, 109, 116, 120, 122,
			127, 136, 143, 145, 152, 160, 164, 166, 172, 180, 188, 196, 204, 211, 219, 227, 235, 242, 250, 258, 266, 273, 282, 299,
			302, 305, 310, 326, 333, 335, 342, 350, 354, 356, 362, 370, 378, 386, 394, 401, 409, 417, 425, 432, 440, 448, 456, 463,
			472, 475, 482, 487, 493, 497, 505, 513, 521, 529, 536, 544, 552, 560, 567, 575, 583, 591, 598, 599};
	}

	private static final short _json_index_offsets[] = init__json_index_offsets_0();

	private static byte[] init__json_trans_targs_0 () {
		return new byte[] {1, 2, 73, 5, 72, 73, 77, 82, 86, 72, 1, 74, 73, 73, 0, 72, 4, 3, 72, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0,
			74, 0, 7, 7, 76, 0, 76, 0, 8, 9, 17, 16, 18, 17, 90, 8, 17, 17, 17, 0, 11, 45, 10, 11, 45, 10, 11, 12, 11, 0, 12, 13,
			25, 26, 15, 25, 32, 37, 41, 15, 12, 27, 25, 25, 0, 15, 24, 14, 15, 24, 14, 15, 16, 90, 15, 0, 16, 9, 17, 18, 17, 90, 16,
			17, 17, 17, 0, 11, 0, 12, 0, 0, 11, 17, 19, 0, 20, 19, 0, 11, 12, 21, 21, 11, 20, 0, 22, 22, 23, 0, 23, 0, 11, 12, 11,
			23, 0, 14, 14, 14, 14, 14, 14, 14, 14, 0, 15, 16, 0, 0, 90, 15, 25, 27, 0, 15, 16, 28, 90, 15, 27, 0, 15, 16, 29, 29,
			90, 15, 28, 0, 30, 30, 31, 0, 31, 0, 15, 16, 90, 15, 31, 0, 15, 16, 0, 0, 33, 90, 15, 25, 15, 16, 0, 0, 34, 90, 15, 25,
			15, 16, 0, 0, 35, 90, 15, 25, 15, 16, 0, 0, 36, 90, 15, 25, 15, 16, 0, 0, 90, 15, 25, 15, 16, 0, 0, 38, 90, 15, 25, 15,
			16, 0, 0, 39, 90, 15, 25, 15, 16, 0, 0, 40, 90, 15, 25, 15, 16, 0, 0, 90, 15, 25, 15, 16, 0, 0, 42, 90, 15, 25, 15, 16,
			0, 0, 43, 90, 15, 25, 15, 16, 0, 0, 44, 90, 15, 25, 15, 16, 0, 0, 90, 15, 25, 10, 10, 10, 10, 10, 10, 10, 10, 0, 46, 47,
			51, 50, 52, 49, 91, 51, 58, 63, 67, 49, 46, 53, 51, 51, 0, 49, 71, 48, 49, 71, 48, 49, 50, 91, 49, 0, 50, 47, 51, 52,
			49, 91, 51, 58, 63, 67, 49, 50, 53, 51, 51, 0, 49, 50, 0, 91, 0, 49, 51, 53, 0, 49, 50, 54, 91, 49, 53, 0, 49, 50, 55,
			91, 55, 49, 54, 0, 56, 56, 57, 0, 57, 0, 49, 50, 91, 49, 57, 0, 49, 50, 0, 91, 59, 0, 49, 51, 49, 50, 0, 91, 60, 0, 49,
			51, 49, 50, 0, 91, 61, 0, 49, 51, 49, 50, 0, 91, 62, 0, 49, 51, 49, 50, 0, 91, 0, 49, 51, 49, 50, 0, 91, 64, 0, 49, 51,
			49, 50, 0, 91, 65, 0, 49, 51, 49, 50, 0, 91, 66, 0, 49, 51, 49, 50, 0, 91, 0, 49, 51, 49, 50, 0, 91, 68, 0, 49, 51, 49,
			50, 0, 91, 69, 0, 49, 51, 49, 50, 0, 91, 70, 0, 49, 51, 49, 50, 0, 91, 0, 49, 51, 48, 48, 48, 48, 48, 48, 48, 48, 0, 72,
			72, 0, 72, 0, 0, 0, 0, 72, 73, 72, 75, 72, 74, 0, 72, 6, 6, 72, 75, 0, 72, 72, 76, 0, 72, 0, 0, 0, 78, 0, 72, 73, 72, 0,
			0, 0, 79, 0, 72, 73, 72, 0, 0, 0, 80, 0, 72, 73, 72, 0, 0, 0, 81, 0, 72, 73, 72, 0, 0, 0, 0, 72, 73, 72, 0, 0, 0, 83, 0,
			72, 73, 72, 0, 0, 0, 84, 0, 72, 73, 72, 0, 0, 0, 85, 0, 72, 73, 72, 0, 0, 0, 0, 72, 73, 72, 0, 0, 0, 87, 0, 72, 73, 72,
			0, 0, 0, 88, 0, 72, 73, 72, 0, 0, 0, 89, 0, 72, 73, 72, 0, 0, 0, 0, 72, 73, 0, 0, 0};
	}

	private static final byte _json_trans_targs[] = init__json_trans_targs_0();

	private static byte[] init__json_trans_actions_0 () {
		return new byte[] {0, 0, 1, 1, 17, 1, 1, 1, 1, 13, 0, 1, 1, 1, 0, 24, 1, 1, 7, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 15, 0, 1, 1, 1, 0, 21, 1, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 17, 1, 1, 1, 1, 13, 0, 1, 1,
			1, 0, 24, 1, 1, 7, 0, 0, 0, 0, 15, 0, 0, 0, 0, 1, 1, 1, 15, 0, 1, 1, 1, 0, 5, 0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 5, 5, 0,
			0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 7, 7, 0, 0, 27, 7, 0, 0, 0, 11, 11, 0, 39, 11,
			0, 0, 9, 9, 0, 0, 33, 9, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 33, 9, 0, 0, 7, 7, 0, 0, 0, 27, 7, 0, 7, 7, 0, 0, 0, 27, 7, 0, 7,
			7, 0, 0, 0, 27, 7, 0, 7, 7, 0, 0, 0, 27, 7, 0, 48, 48, 0, 0, 62, 48, 0, 7, 7, 0, 0, 0, 27, 7, 0, 7, 7, 0, 0, 0, 27, 7,
			0, 7, 7, 0, 0, 0, 27, 7, 0, 51, 51, 0, 0, 70, 51, 0, 7, 7, 0, 0, 0, 27, 7, 0, 7, 7, 0, 0, 0, 27, 7, 0, 7, 7, 0, 0, 0,
			27, 7, 0, 45, 45, 0, 0, 54, 45, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 1, 0, 1, 17, 19, 1, 1, 1, 1, 13, 0, 1, 1, 1, 0, 24,
			1, 1, 7, 0, 0, 0, 0, 19, 0, 0, 0, 0, 1, 1, 17, 19, 1, 1, 1, 1, 13, 0, 1, 1, 1, 0, 7, 7, 0, 30, 0, 7, 0, 0, 0, 11, 11, 0,
			42, 11, 0, 0, 9, 9, 0, 36, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 36, 9, 0, 0, 7, 7, 0, 30, 0, 0, 7, 0, 7, 7, 0, 30, 0, 0,
			7, 0, 7, 7, 0, 30, 0, 0, 7, 0, 7, 7, 0, 30, 0, 0, 7, 0, 48, 48, 0, 66, 0, 48, 0, 7, 7, 0, 30, 0, 0, 7, 0, 7, 7, 0, 30,
			0, 0, 7, 0, 7, 7, 0, 30, 0, 0, 7, 0, 51, 51, 0, 74, 0, 51, 0, 7, 7, 0, 30, 0, 0, 7, 0, 7, 7, 0, 30, 0, 0, 7, 0, 7, 7, 0,
			30, 0, 0, 7, 0, 45, 45, 0, 58, 0, 45, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 7, 0, 0, 0, 0, 7, 0, 11, 0, 11, 0, 0, 9, 0,
			0, 9, 0, 0, 9, 9, 0, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0,
			48, 0, 0, 0, 0, 48, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 51, 0, 0, 0, 0, 51, 0, 7,
			0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 45, 0, 0, 0, 0, 45, 0, 0, 0, 0};
	}

	private static final byte _json_trans_actions[] = init__json_trans_actions_0();

	private static byte[] init__json_eof_actions_0 () {
		return new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 11,
			9, 9, 7, 7, 7, 7, 48, 7, 7, 7, 51, 7, 7, 7, 45, 0, 0};
	}

	private static final byte _json_eof_actions[] = init__json_eof_actions_0();

	static final int json_start = 1;
	static final int json_first_final = 72;
	static final int json_error = 0;

	static final int json_en_object = 8;
	static final int json_en_array = 46;
	static final int json_en_main = 1;

	// line 236 "JsonReader.rl"

	private final Array<JsonValue> elements = new Array(8);
	private final Array<JsonValue> lastChild = new Array(8);
	private JsonValue root, current;

	private void addChild (String name, JsonValue child) {
		child.setName(name);
		if (current == null) {
			current = child;
			root = child;
		} else if (current.isArray() || current.isObject()) {
			if (current.size == 0)
				current.child = child;
			else {
				JsonValue last = lastChild.pop();
				last.next = child;
				child.prev = last;
			}
			lastChild.add(child);
			current.size++;
		} else
			root = current;
	}

	protected void startObject (String name) {
		JsonValue value = new JsonValue(ValueType.object);
		if (current != null) addChild(name, value);
		elements.add(value);
		current = value;
	}

	protected void startArray (String name) {
		JsonValue value = new JsonValue(ValueType.array);
		if (current != null) addChild(name, value);
		elements.add(value);
		current = value;
	}

	protected void pop () {
		root = elements.pop();
		if (current.size > 0) lastChild.pop();
		current = elements.size > 0 ? elements.peek() : null;
	}

	protected void string (String name, String value) {
		addChild(name, new JsonValue(value));
	}

	protected void number (String name, double value) {
		addChild(name, new JsonValue(value));
	}

	protected void number (String name, long value) {
		addChild(name, new JsonValue(value));
	}

	protected void bool (String name, boolean value) {
		addChild(name, new JsonValue(value));
	}

	private String unescape (String value) {
		int length = value.length();
		StringBuilder buffer = new StringBuilder(length + 16);
		for (int i = 0; i < length;) {
			char c = value.charAt(i++);
			if (c != '\\') {
				buffer.append(c);
				continue;
			}
			if (i == length) break;
			c = value.charAt(i++);
			if (c == 'u') {
				buffer.append(Character.toChars(Integer.parseInt(value.substring(i, i + 4), 16)));
				i += 4;
				continue;
			}
			switch (c) {
			case '"':
			case '\\':
			case '/':
				break;
			case 'b':
				c = '\b';
				break;
			case 'f':
				c = '\f';
				break;
			case 'n':
				c = '\n';
				break;
			case 'r':
				c = '\r';
				break;
			case 't':
				c = '\t';
				break;
			default:
				throw new SerializationException("Illegal escaped character: \\" + c);
			}
			buffer.append(c);
		}
		return buffer.toString();
	}
}
