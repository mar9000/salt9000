/**
 * This file is part of the Salt9000 project.
 * 
 * Salt9000 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Salt9000 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with the Salt9000 project.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Copyright 2014 Marco LOMBARDO, mar9000 near google.com .
 */

grammar Salt;

/**
 * A document is basically the main group.
 */
document: blanks group blanks;

/**
 * Everything between { and }.
 */
group: OPEN (row NL)* row? blanks CLOSE;

/**
 * A row skips spaces and new lines at the beginning, then with cells we can have only
 * cellContent, spaces and column separators.
 */
row: blanks cell (COLUMN_SEPARATOR cell)*;

cell: SP* (cellContent | group | emptyCell | menuBar | tabBar) SP*;

menuBar: OPEN_MENU menu (COLUMN_SEPARATOR menu)* CLOSE;
menu: SP* text SP*;

tabBar: OPEN_TAB tab (COLUMN_SEPARATOR tab)* CLOSE;
tab: SP* SELECTED_TAB? text SP*;

emptyCell: '.';

cellContent: button | radioChecked | radioUnchecked | checkedBox | uncheckedBox
           | dropList
           | string | text ;

button: '[' SP* text ']';
radioChecked: '(X)' SP* text;
radioUnchecked: '()' SP* text;
checkedBox: '[X]' SP* text;
uncheckedBox: '[]' SP* text;
dropList: '^' SP* text '^';
string: '"' .*? '"';
text: (CHAR | DIGIT | SPECIAL_CHAR) (CHAR | DIGIT | SPECIAL_CHAR | SP)*;

blanks: (SP | NL)*;

/**
 * Lexer.
 */
 
OPEN: OPEN_EMPTY | OPEN_BOTH | OPEN_VERTICAL | OPEN_HORIZONTAL | OPEN_EXTERNAL;
OPEN_EMPTY: '{';
OPEN_BOTH: '{#';
OPEN_VERTICAL: '{!';
OPEN_HORIZONTAL: '{-';
OPEN_EXTERNAL: '{+';
OPEN_MENU: '{*';
OPEN_TAB: '{/';
SELECTED_TAB: '<b>';
CLOSE: '}';

COLUMN_SEPARATOR: '|';

NL: '\r'? '\n';

CHAR: [a-zA-Z];

SPECIAL_CHAR: ':' | '-' | '_' | '.' | '*' | '`' | '\\' | '!' | '$' | '%' | '&' | '/' | '(' | ')'
            | '=' | '?' | '\'' | '#' | '@' | '[' | ']' | '+' | ';' | ',' | '<' | '>';

DIGIT:[0-9];

SP: ' ' | '\t';
