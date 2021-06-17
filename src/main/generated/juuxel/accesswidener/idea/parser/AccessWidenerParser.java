// This is a generated file. Not intended for manual editing.
package juuxel.accesswidener.idea.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static juuxel.accesswidener.idea.psi.AwTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class AccessWidenerParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return accessWidener(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(CLASS_DEFINITION, FIELD_DEFINITION, METHOD_DEFINITION),
  };

  /* ********************************************************** */
  // header line_*
  static boolean accessWidener(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accessWidener")) return false;
    if (!nextTokenIs(b, HEADER_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = header(b, l + 1);
    r = r && accessWidener_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // line_*
  private static boolean accessWidener_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accessWidener_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!line_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "accessWidener_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ACCESS TARGET_TYPE CLASS_NAME
  public static boolean classDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDefinition")) return false;
    if (!nextTokenIs(b, ACCESS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ACCESS, TARGET_TYPE, CLASS_NAME);
    exit_section_(b, m, CLASS_DEFINITION, r);
    return r;
  }

  /* ********************************************************** */
  // methodDefinition|fieldDefinition|classDefinition
  static boolean definition_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "definition_")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = methodDefinition(b, l + 1);
    if (!r) r = fieldDefinition(b, l + 1);
    if (!r) r = classDefinition(b, l + 1);
    exit_section_(b, l, m, r, false, AccessWidenerParser::lineRecover_);
    return r;
  }

  /* ********************************************************** */
  // ACCESS TARGET_TYPE CLASS_NAME MEMBER_NAME typeDescriptor
  public static boolean fieldDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDefinition")) return false;
    if (!nextTokenIs(b, ACCESS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ACCESS, TARGET_TYPE, CLASS_NAME, MEMBER_NAME);
    r = r && typeDescriptor(b, l + 1);
    exit_section_(b, m, FIELD_DEFINITION, r);
    return r;
  }

  /* ********************************************************** */
  // HEADER_START VERSION NAMESPACE
  public static boolean header(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "header")) return false;
    if (!nextTokenIs(b, HEADER_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, HEADER_START, VERSION, NAMESPACE);
    exit_section_(b, m, HEADER, r);
    return r;
  }

  /* ********************************************************** */
  // !(ACCESS|COMMENT)
  static boolean lineRecover_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "lineRecover_")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !lineRecover__0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ACCESS|COMMENT
  private static boolean lineRecover__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "lineRecover__0")) return false;
    boolean r;
    r = consumeToken(b, ACCESS);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  /* ********************************************************** */
  // definition_|COMMENT
  static boolean line_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "line_")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = definition_(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    exit_section_(b, l, m, r, false, AccessWidenerParser::lineRecover_);
    return r;
  }

  /* ********************************************************** */
  // LITERAL_START CLASS_NAME SEMICOLON
  static boolean literalTypeDescriptor_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalTypeDescriptor_")) return false;
    if (!nextTokenIs(b, LITERAL_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LITERAL_START, CLASS_NAME, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ACCESS TARGET_TYPE CLASS_NAME MEMBER_NAME methodDescriptor
  public static boolean methodDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodDefinition")) return false;
    if (!nextTokenIs(b, ACCESS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ACCESS, TARGET_TYPE, CLASS_NAME, MEMBER_NAME);
    r = r && methodDescriptor(b, l + 1);
    exit_section_(b, m, METHOD_DEFINITION, r);
    return r;
  }

  /* ********************************************************** */
  // L_PAREN typeDescriptor* R_PAREN typeDescriptor
  public static boolean methodDescriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodDescriptor")) return false;
    if (!nextTokenIs(b, L_PAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_PAREN);
    r = r && methodDescriptor_1(b, l + 1);
    r = r && consumeToken(b, R_PAREN);
    r = r && typeDescriptor(b, l + 1);
    exit_section_(b, m, METHOD_DESCRIPTOR, r);
    return r;
  }

  // typeDescriptor*
  private static boolean methodDescriptor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodDescriptor_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!typeDescriptor(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "methodDescriptor_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // literalTypeDescriptor_|PRIMITIVE_DESCRIPTOR|ARRAY typeDescriptor
  public static boolean typeDescriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeDescriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_DESCRIPTOR, "<type descriptor>");
    r = literalTypeDescriptor_(b, l + 1);
    if (!r) r = consumeToken(b, PRIMITIVE_DESCRIPTOR);
    if (!r) r = typeDescriptor_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ARRAY typeDescriptor
  private static boolean typeDescriptor_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeDescriptor_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ARRAY);
    r = r && typeDescriptor(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}
