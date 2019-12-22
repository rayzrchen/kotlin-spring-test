package com.example.kotlinspringboot

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer
import org.junit.jupiter.api.Test
import org.apache.lucene.index.memory.MemoryIndex
import org.apache.lucene.queryparser.flexible.standard.StandardQueryParser
import org.assertj.core.api.Assertions.assertThat


class LuceneTest {
    @Test
    internal fun query1() {
        val analyzer = SmartChineseAnalyzer()
        val index = MemoryIndex(true)
        index.addField("content", "this is a credential information, no one else should know, it is not our secret, it is public", analyzer)
        val query = StandardQueryParser(analyzer).parse("+this +is +a +(credential secret unknown)", "content")

        val indexSearcher = index.createSearcher()
        val explain = indexSearcher.explain(query, 0)

        println(explain.isMatch)
        println(explain.value)
    }

    @Test
    internal fun `fine lexicon`() {
        val s = "ab | bc is ok 阿根廷"
        assertThat(ensureOrIsInTheGroup(s)).isEqualTo("+(ab bc) +is +ok +阿根廷")
        val s1 = "a|b is ok"
        assertThat(ensureOrIsInTheGroup(s1)).isEqualTo("+(a b) +is +ok")
        val s2 = "{{a|b | c} is ok|fine|good} | {hello world}"
        assertThat(ensureOrIsInTheGroup(s2)).isEqualTo("(+(a b c) +is +(ok fine good)) (\"hello world\")")
    }

    private fun ensureOrIsInTheGroup(s: String): String {
        return s.replace(Regex("(\\p{IsAlphabetic}+[ ]*(\\|[ ]*\\p{IsAlphabetic}+[ ]*)+)"), "{$1}" )
    }

    private fun replaceAllOr(s: String): String {
        return ""
    }

}
