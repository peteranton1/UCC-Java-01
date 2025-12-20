package org.example.ast.qsl1;

sealed interface Qsl1Value
    permits
    Qsl1Id,
    Qsl1Text,
    Qsl1Number,
    Qsl1Null,
    Qsl1Boolean,
    Qsl1List,
    Qsl1Map
{ }

non-sealed interface Qsl1Id extends Qsl1Value {}
non-sealed interface Qsl1Text extends Qsl1Value {}
non-sealed interface Qsl1Number extends Qsl1Value {}
non-sealed interface Qsl1Null extends Qsl1Value {}
non-sealed interface Qsl1Boolean extends Qsl1Value {}
non-sealed interface Qsl1List extends Qsl1Value {}
non-sealed interface Qsl1Map extends Qsl1Value {}

